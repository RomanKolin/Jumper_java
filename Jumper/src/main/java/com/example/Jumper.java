package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;

public class Jumper
{
    public static void main(String[] args)
    {
        int noobst = 0, nojump = 0, scor = 0;
        int[] obstcoordarr, obsttyparr, jumpstartarr, jumpsrangarr, jumpsendarr;
        Map<Integer, Integer> pointmap = new LinkedHashMap<>();

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\u001B[38;2;255;0;0mYou should jump over obstacles. Obstacle types: 1 (1 point), 2 (3 points), 3 (5 points)\nEnter the data separated by space (press Enter to continue)\u001B[0m");
            br.readLine();

            System.out.print("Number of obstacles: ");
            noobst = Integer.parseInt(br.readLine());

            System.out.print("Obstacle coordinates: ");
            obstcoordarr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (noobst != obstcoordarr.length)
            {
                System.out.print("Number of obstacle coordinates must be equal to the number of obstacles");
                return;
            }
            for (int i = 0; i < obstcoordarr.length-1; i++)
                if (obstcoordarr[i]+1 >= obstcoordarr[i+1])
                {
                    System.out.print("Obstacles cannot intersect or placed one after the other");
                    return;
                }

            System.out.print("Obstacle types: ");
            obsttyparr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (noobst != obsttyparr.length)
            {
                System.out.print("Number of obstacle types must be equal to the number of obstacles");
                return;
            }
            for (int i = 0; i < obsttyparr.length-1; i++)
                if (obsttyparr[i] != 1 && obsttyparr[i] != 2 && obsttyparr[i] != 3)
                {
                    System.out.print("Obstacle types must be 1, 2, or 3");
                    return;
                }

            System.out.print("Number of jumps: ");
            nojump = Integer.parseInt(br.readLine());

            System.out.print("Jump start coordinates: ");
            jumpstartarr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (nojump != jumpstartarr.length)
            {
                System.out.print("Number of jump start coordinates must be equal to the number of jumps");
                return;
            }
            for (int i = 0; i < jumpstartarr.length-1; i++)
                if (jumpstartarr[i]+1 >= jumpstartarr[i+1])
                {
                    System.out.print("You cannot jump from the same or previous coordinate");
                    return;
                }

            System.out.print("Jump ranges: ");
            jumpsrangarr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (nojump != jumpsrangarr.length)
            {
                System.out.print("Number of jump ranges must be equal to the number of jumps");
                return;
            }
            br.close();
        }
        catch (Exception e)
        {
            System.out.print("Wrong data");
            return;
        }

        for (int i = 0; i < obstcoordarr.length; i++)
            if (obsttyparr[i] == 1)
                pointmap.put(obstcoordarr[i], 1);
            else if (obsttyparr[i] == 2)
                pointmap.put(obstcoordarr[i], 3);
            else if (obsttyparr[i] == 2)
                pointmap.put(obstcoordarr[i], 5);
        jumpsendarr = new int[nojump];
        for (int i = 0; i < nojump; i++)
            jumpsendarr[i] = jumpstartarr[i]+jumpsrangarr[i];
        int point = 0;
        for (int i = 0; i < jumpsendarr.length; i++)
        {
            if (point >= noobst)
                break;
            if (jumpstartarr[i]>obstcoordarr[point] || jumpsendarr[i] <= obstcoordarr[point])
            {
                scor-=1;
                point+=1;
            }
            else
                while (jumpsendarr[i] > obstcoordarr[point])
                {
                    scor+=pointmap.get(obstcoordarr[point]);
                    point+=1;
                    if (point >= noobst)
                        break;
                }
        }
        if (scor > 0)
            System.out.print("Score: " + scor);
        else
            System.out.print("Score: 0");
    }
}