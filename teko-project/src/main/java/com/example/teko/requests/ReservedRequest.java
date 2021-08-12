package com.example.teko.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ReservedRequest {
    private ArrayList<Seat> reservedPairArray;
}
