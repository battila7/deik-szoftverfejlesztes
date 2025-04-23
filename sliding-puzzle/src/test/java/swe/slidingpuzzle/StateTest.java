package swe.slidingpuzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {
    @Test
    public void startingStateShouldCreateTheStartingState() {
        // When
        var state = State.startingState();

        // Then
        assertEquals(
                new Piece(0, 0),
                state.getBlock()
        );
    }
}