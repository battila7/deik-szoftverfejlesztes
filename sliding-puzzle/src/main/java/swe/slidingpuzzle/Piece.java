package swe.slidingpuzzle;

public record Piece(
        int row,
        int column
) {
    public Piece {
        if ((row < 0) || (row > 2)) {
            throw new IndexOutOfBoundsException();
        }

        if ((column < 0) || (column > 2)) {
            throw new IndexOutOfBoundsException();
        }
    }
};
