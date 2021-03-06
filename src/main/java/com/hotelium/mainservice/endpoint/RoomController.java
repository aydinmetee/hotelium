package com.hotelium.mainservice.endpoint;

import com.hotelium.mainservice.dto.RoomReadDTO;
import com.hotelium.mainservice.dto.RoomSearchCriteriaDTO;
import com.hotelium.mainservice.dto.RoomWriteDTO;
import com.hotelium.mainservice.serviceview.RoomServiceView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{roomId}")
    @ApiOperation(value = "Update Room", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> update(@PathVariable("roomId") String roomId,
                                              @RequestBody RoomWriteDTO roomWriteDTO) {
        return ResponseEntity.ok(roomServiceView.update(roomId, roomWriteDTO));
    }

    @GetMapping("/{roomId}")
    @ApiOperation(value = "Get By Id Room", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> getById(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(roomServiceView.getById(roomId));
    }

    @DeleteMapping("/{roomId}")
    @ApiOperation(value = "Delete Room", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> delete(@PathVariable("roomId") String customerId) {
        return ResponseEntity.ok(roomServiceView.delete(customerId));
    }

    @PostMapping("/search")
    @ApiOperation(value = "Create Room", response = Page.class)
    public ResponseEntity<Page<RoomReadDTO>> search(@RequestBody() RoomSearchCriteriaDTO filter,
                                                    Pageable pageable) {
        return ResponseEntity.ok(roomServiceView.search(filter, pageable));
    }

    @GetMapping("/{roomId}/mark-as-clean")
    @ApiOperation(value = "Mark as clean", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> markAsClean(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(roomServiceView.markAsClean(roomId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find")
    @ResponseBody
    public ResponseEntity<List<RoomReadDTO>> find(@RequestParam(value = "search") String search) {
        return ResponseEntity.ok(roomServiceView.find(search));
    }
}
