package nextstep.ladder.domain.line;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class DirectionTest {
    private DirectionSelector directionSelector;

    @BeforeEach
    public void setUp() {
        directionSelector = () -> true;
    }
    @Test
    public void init() {
        assertThat(Direction.of(true, false))
                .isEqualTo(Direction.of(true, false));
    }

    @Test
    public void init_invalid() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> Direction.of(TRUE, TRUE));
    }

    @Test
    public void next_random_true() {
        Direction next = Direction.first(TRUE).next(directionSelector);
        assertThat(next).isEqualTo(Direction.of(TRUE, FALSE));
    }

    @Test
    public void next_random_false() {
        for (int i = 0; i < 100; i++) {
            Direction.first(FALSE).next(directionSelector);
        }
    }

    @Test
    public void next_true() {
        Direction next = Direction.of(TRUE, FALSE).next(TRUE);
        assertThat(next).isEqualTo(Direction.of(FALSE, TRUE));
    }

    @Test
    public void next_false() {
        Direction next = Direction.of(FALSE, TRUE).next(FALSE);
        assertThat(next).isEqualTo(Direction.of(TRUE, FALSE));
    }

    @Test
    public void first() {
        Direction first = Direction.first(TRUE);
        assertThat(first.isLeft()).isEqualTo(FALSE);
    }

    @Test
    public void last() {
        Direction last = Direction.first(TRUE).last();
        assertThat(last).isEqualTo(Direction.of(TRUE, FALSE));
    }
}