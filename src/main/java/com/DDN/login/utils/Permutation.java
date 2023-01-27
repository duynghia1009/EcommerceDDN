package com.DDN.login.utils;

import java.util.LinkedList;
import java.util.List;

public class Permutation {
    private List<String> output;

    public Permutation(String[] input) {
        if(input.length == 0) {
            return;
        }
        output = new LinkedList<>();
        permute(input, 0, input.length - 1);
    }

    public void permute(String[] input, int l, int r) {
        if(l==r) {
            output.add(String.join(" ", input));
        }
        else {
            for(int i = l; i <= r; i++) {
                input = swap(input,l,i);
                permute(input, l + 1, r);
                input = swap(input,l,i);
            }
        }
    }

    private String[] swap(String[] strinArr, int i, int j) {
        String temp =  strinArr[i];
        strinArr[i] = strinArr[j];
        strinArr[j] = temp;
        return strinArr;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }
}
