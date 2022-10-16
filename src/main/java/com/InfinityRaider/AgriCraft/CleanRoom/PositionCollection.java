package com.InfinityRaider.AgriCraft.CleanRoom;

import java.util.ArrayList;
import java.util.List;

public class PositionCollection {
    List<PositionPack> pack = new ArrayList<>();

    static class PositionPack {
        int x;

        int y;

        int z;

        boolean isSpecial;

        public PositionPack(int x, int y, int z) {
            this(x, y, z, false);
        }

        public PositionPack(int x, int y, int z, boolean isSpecial) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.isSpecial = isSpecial;
        }
    }
}
