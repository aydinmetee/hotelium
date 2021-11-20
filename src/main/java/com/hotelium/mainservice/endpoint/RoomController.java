package com.hotelium.mainservice.endpoint;

import com.hotelium.mainservice.dto.RoomReadDTO;
import com.hotelium.mainservice.dto.RoomSearchCriteriaDTO;
import com.hotelium.mainservice.dto.RoomWriteDTO;
import com.hotelium.mainservice.serviceview.RoomServiceView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Api(value = "/room")
public class RoomController {

    private final RoomServiceView roomServiceView;

    @PostMapping()
    @ApiOperation(value = "Create Room", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> create(@RequestBody RoomWriteDTO roomWriteDTO) {
        return ResponseEntity.ok(roomServiceView.create(roomWriteDTO));
    }

    @GetMapping("/{roomId}")
    @ApiOperation(value = "Get By Id Room", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> getById(@PathVariable("roomId") Long roomId) {
        return ResponseEntity.ok(roomServiceView.getById(roomId));
    }

    @DeleteMapping("/{roomId}")
    @ApiOperation(value = "Delete Room", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> delete(@PathVariable("roomId") Long customerId) {
        return ResponseEntity.ok(roomServiceView.delete(customerId));
    }

    @PostMapping("/search")
    @ApiOperation(value = "Create Room", response = Page.class)
    public ResponseEntity<Page<RoomReadDTO>> search(@RequestBody() RoomSearchCriteriaDTO filter,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(roomServiceView.search(filter, PageRequest.of(page, size)));
    }

    @GetMapping("/{roomId}/mark-as-clean")
    @ApiOperation(value = "Mark as clean", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> markAsClean(@PathVariable("roomId") Long roomId) {
        return ResponseEntity.ok(roomServiceView.markAsClean(roomId));
    }
}
