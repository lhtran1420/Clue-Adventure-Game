package com.example.teko.controllers;

import com.example.teko.entity.CinemaOperationSeat;
import com.example.teko.requests.CheckSeatRequest;
import com.example.teko.requests.ReservedRequest;
import com.example.teko.resources.CinemaOperationResource;
import com.example.teko.services.CinemaOperationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Api(tags = "Category Ops")
@RestController
@AllArgsConstructor
@Validated
public class CinemaOperationController {

    @Autowired
    private final CinemaOperationService cinemaOperationService;

    @GetMapping("/v1/cinema-seat")
    public CinemaOperationResource showCinemaSeat() {
        CinemaOperationSeat cinemaOperationSeat = cinemaOperationService.showCinemaSeat();
        return convertFromEntityToResource(cinemaOperationSeat);
    }

    @GetMapping("/v1/cinema-operation")
    public CinemaOperationResource getCinemaSeatByRequest(
            @Valid CheckSeatRequest request
    ) {
        CinemaOperationSeat cinemaOperationSeat = cinemaOperationService.getCinemaSeat(request.getNumberOfSeat());
        return convertFromEntityToResource(cinemaOperationSeat);
    }

    @PostMapping("/v1/cinema-operation/")
    public CinemaOperationResource reservedCinemaSeat(
            @RequestBody ReservedRequest request
    ) {
        CinemaOperationSeat cinemaOperationSeat = cinemaOperationService.updateCinemaSeat(request.getReservedPairArray());
        return convertFromEntityToResource(cinemaOperationSeat);
    }

    private CinemaOperationResource convertFromEntityToResource(CinemaOperationSeat cinemaOperationSeat) {
        CinemaOperationResource cinemaOperationResource = new CinemaOperationResource();
        cinemaOperationResource.setCinemaOperationSeat(cinemaOperationSeat.getCinemaOperationSeat());
        cinemaOperationResource.setMessage(cinemaOperationSeat.getMessage());

        return cinemaOperationResource;
    }
}
