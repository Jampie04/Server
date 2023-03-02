package com.example.server.resource;

import com.example.server.enumeration.Status;
import com.example.server.model.Response;
import com.example.server.model.Server;
import com.example.server.service.implementation.ServerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("servers", serverService.list(30)))
                .message("Servers retrieve")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }
    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("server", server))
                .message(server.getStatus() == Status.SERVER_UP ? "Ping succes" : "Ping failed")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }
    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                .timeStamp(now())
                .data(Map.of("server", serverService.create(server)))
                .message("Server created")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id){
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("server", serverService.get(id)))
                .message("Server retrieved")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id){
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("deleted", serverService.delete(id)))
                .message("Server deleted")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + fileName));
    }
}
