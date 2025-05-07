package swe.slidingpuzzle;



public class State {
    private final Piece redShoe;
    private final Piece blueShoe;
    private final Piece blackShoe;
    private final Piece block;

    public State(Piece redShoe, Piece blueShoe, Piece blackShoe, Piece block) {
        this.redShoe = redShoe;
        this.blueShoe = blueShoe;
        this.blackShoe = blackShoe;
        this.block = block;
    }

    public static State startingState() {
        return new State(
            new Piece(
                    2, 0
            ),
            new Piece(
                1, 1
            ),
            new Piece(
                    0, 2
            ),
            new Piece(
                    0, 0
            )
        );
    }

    public Piece getRedShoe() {
        return redShoe;
    }

    public Piece getBlueShoe() {
        return blueShoe;
    }

    public Piece getBlackShoe() {
        return blackShoe;
    }

    public Piece getBlock() {
        return block;
    }

    public State moveUp() {
        if (block.row() - 1 < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (block.row() - 1 == blackShoe.row()) {
            throw new TargetTileOccupiedException();
        }

        return null;
    }
}
