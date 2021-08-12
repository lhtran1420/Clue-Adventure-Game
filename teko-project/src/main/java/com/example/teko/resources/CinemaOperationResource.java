package com.example.teko.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CinemaOperationResource {
    String[][] cinemaOperationSeat;
    List<String> message;
}
