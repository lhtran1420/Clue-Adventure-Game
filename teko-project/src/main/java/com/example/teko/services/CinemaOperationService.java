package com.example.teko.services;

import com.example.teko.entity.CinemaOperationSeat;
import com.example.teko.requests.Seat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor

public class CinemaOperationService {

    static String arr[][];
    static int d;
    static int rows;
    static int columns;

    static {
        Random rand = new Random();
        rows = rand.nextInt(5)+10;
        columns = rand.nextInt(5) +15;
        arr = new String[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                arr[i][j] = "A";
            }
        }

        d = rand.nextInt(3)+2;
        System.out.println("d la "  + d);
    }

    private void markUnavailableSeat(String[][] stringArr, Seat seat, String socialDistance, String reserved) {
        Integer x = seat.getRow();
        int y = seat.getColumn();
        int distance = d -1;
        for (int i = 0; i < distance; i++) {
            if(x-distance+i>=0 && x-distance+i<rows && y-i>=0 && y-i<columns && stringArr[x-distance+i][y-i].equals("A")) {
                stringArr[x-distance+i][y-i] = socialDistance;
            }
            if(x-i>=0 && x-i<rows && y+distance-i>=0 && y+distance-i<columns && stringArr[x-i][y+distance-i].equals("A")) {
                stringArr[x-i][y+distance-i] = socialDistance;
            }

            if(x+i>=0 && x+i<rows && y-distance+i>=0 && y-distance+i<columns && stringArr[x+i][y-distance+i].equals("A")) {
                stringArr[x+i][y-distance+i] = socialDistance;
            }
            if(x+distance-i>=0 && x+distance-i<rows && y+i>=0 && y+i<columns && stringArr[x+distance-i][y+i].equals("A")) {
                stringArr[x+distance-i][y+i] = socialDistance;
            }
        }

        stringArr[x][y] = reserved;
    }

    public CinemaOperationSeat showCinemaSeat() {
        CinemaOperationSeat cinemaOperationSeat = new CinemaOperationSeat();
        cinemaOperationSeat.setCinemaOperationSeat(arr);
        List<String> ls = new ArrayList<>();
        ls.add("");
        cinemaOperationSeat.setMessage(ls);
        System.out.println("d la "  + d);
        return cinemaOperationSeat;
    }

    public CinemaOperationSeat getCinemaSeat(int n) {
        String[][] cur = new String[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                cur[i][j] = arr[i][j];
            }
        }

        int count = 0, br = 0;
        CinemaOperationSeat cinemaOperationSeat = new CinemaOperationSeat();
        List<String> ls = new ArrayList<>();

        for (int i = 0; i < cur.length; i++) {
            for (int j = 0; j < cur[i].length; j++) {
                if (cur[i][j].equals("A")) {
                    Seat seat = new Seat();
                    seat.setRow(i);
                    seat.setColumn(j);
                    count++;
                    String s = "The seat available to seat is at row "+ i + " and column "+j;
                    ls.add(s);
                    markUnavailableSeat(cur,seat,"D","S");
                }

                if (count == n) {
                    br = 1;
                    break;
                }
            }

            if (br == 1) break;
        }

        if (count == n) {
            cinemaOperationSeat.setCinemaOperationSeat(cur);
            for (int i = 0; i < cur.length; i++) {
                for (int j = 0; j < cur[i].length; j++) {
                    System.out.print(cur[i][j]+" ");
                }
                System.out.println();
            }

            System.out.println();
            ls.add("It is available seat for " + n + " people ");
            cinemaOperationSeat.setMessage(ls);
            return cinemaOperationSeat;
        }

        List<String> ls1 = new ArrayList<>();
        ls1.add("It is not available seat for " + n + " people ");
        cinemaOperationSeat.setMessage(ls1);
        String empty[][] = new String[rows][columns];
        cinemaOperationSeat.setCinemaOperationSeat(empty);
        return cinemaOperationSeat;
    }

    public CinemaOperationSeat updateCinemaSeat(ArrayList<Seat> pairs) {
        String[][] current = new String[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                current[i][j] = arr[i][j];
            }
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < pairs.size(); i++) {
            if (current[pairs.get(i).getRow()][pairs.get(i).getColumn()].equals("R") ||
                    current[pairs.get(i).getRow()][pairs.get(i).getColumn()].equals("D")) {
                ls.add("Seat at row " + pairs.get(i).getRow() + " and column " + pairs.get(i).getColumn()
                        + " is not available to reserve. Please try again");
            }

            else {
                System.out.println(pairs.get(i).getRow()+"  "+pairs.get(i).getColumn());
                markUnavailableSeat(current, pairs.get(i),"D", "R");
            }
        }

        CinemaOperationSeat cinemaOperationSeat = new CinemaOperationSeat();


        if(ls.size()>=1) {
            String empty[][] = new String[rows][columns];
            cinemaOperationSeat.setCinemaOperationSeat(empty);
            cinemaOperationSeat.setMessage(ls);
            return cinemaOperationSeat;
        }

        List<String> ls1 = new ArrayList<>();
        String success = "It is available for all of your reserved seat and these seat have been reserved";
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                arr[i][j] = current[i][j];
            }
        }

        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                System.out.print(arr[i][j]+" ");
            }

            System.out.println();
        }

        ls1.add(success);
        cinemaOperationSeat.setMessage(ls1);
        cinemaOperationSeat.setCinemaOperationSeat(arr);

        return cinemaOperationSeat;
    }
}
