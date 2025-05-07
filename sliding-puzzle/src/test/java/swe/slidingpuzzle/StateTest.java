package swe.slidingpuzzle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

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

    @Test
    @DisplayName("Cannot leave the tray by moving up")
    public void cannotLeaveTheTrayByMovingUp() {
        // Arrange / Given
        final var initialState = State.startingState();

        // Assert / Then
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> {
                    // Act / When
                    initialState.moveUp();
                }
        );
    }

    public static List<Arguments> asd() {
        return List.of(
                Arguments.of(
                        new State(
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
                                        1, 2
                                )
                        )
                ),
                Arguments.of(
                        new State(
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
                                        2, 1
                                )
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("asd")
    public void cannotMoveUpToATileOccupiedByTheBlackShoe(State initialState) {
        // Assert / Then
        assertThrows(
                TargetTileOccupiedException.class,
                () -> {
                    // Act / When
                    initialState.moveUp();
                }
        );
    }

    @Test
    public void cannotMoveUpToATileOccupiedByTheRedShoe() {
        // Arrange / Given
        final var initialState = new State(
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
                        1, 2
                )
        );

        // Assert / Then
        assertThrows(
                TargetTileOccupiedException.class,
                () -> {
                    // Act / When
                    initialState.moveUp();
                }
        );
    }
}