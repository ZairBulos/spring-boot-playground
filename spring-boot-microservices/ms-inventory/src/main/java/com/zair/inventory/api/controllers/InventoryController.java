package com.zair.inventory.api.controllers;

import com.zair.inventory.api.dtos.InventoryRequestDto;
import com.zair.inventory.api.dtos.InventoryResponseDto;
import com.zair.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService service;

    @GetMapping("/{sku}")
    public ResponseEntity<InventoryResponseDto> read(@PathVariable String sku) {
        return ResponseEntity.ok(service.read(sku));
    }

    @PutMapping("/{sku}/increase")
    public ResponseEntity<InventoryResponseDto> increase(@PathVariable String sku,
                                                         @RequestBody InventoryRequestDto inventoryRequest) {
        return ResponseEntity.ok(service.increase(sku, inventoryRequest.quantity()));
    }

    @PutMapping("/{sku}/decrease")
    public ResponseEntity<InventoryResponseDto> decrease(@PathVariable String sku,
                                                         @RequestBody InventoryRequestDto inventoryRequest) {
        return ResponseEntity.ok(service.decrease(sku, inventoryRequest.quantity()));
    }
}
