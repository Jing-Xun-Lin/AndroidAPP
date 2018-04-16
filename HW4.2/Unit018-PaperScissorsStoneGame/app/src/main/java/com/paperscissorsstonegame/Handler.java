package com.paperscissorsstonegame;

public class Handler {
    public int foo(int player, int com) {
        // 1 – 剪刀, 2 – 石頭, 3 – 布.

        if (com == 1){
            if (player == 1)
                return 3;
            else if (player == 2)
                return 1;
            else
                return 2;
        }
        else if (com == 2){
            if (player == 1)
                return 2;
            else if (player == 2)
                return 3;
            else
                return 1;
        }
        else{
            if (player == 1)
                return 1;
            else if (player == 2)
                return 2;
            else
                return 3;
        }
    }
}
