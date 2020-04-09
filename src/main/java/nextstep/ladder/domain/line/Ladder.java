package nextstep.ladder.domain.line;

import nextstep.ladder.domain.game.LadderSize;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ladder implements Iterable<LadderLine> {
    private List<LadderLine> ladder;

    public static Ladder valueOf(LadderSize ladderSize) {
        Ladder ladder = new Ladder(generateHorizontalLines(ladderSize));
        return ladder;
    }

    private static List<LadderLine> generateHorizontalLines(
            LadderSize ladderSize) {
        List<LadderLine> ladderLines = new ArrayList<>();
        for (int i = 0, size = ladderSize.getHeight(); i < size; i++) {
            ladderLines.add(LadderLine.init(ladderSize.getWidth()));
        }

        return ladderLines;
    }

    private Ladder(List<LadderLine> ladderLines) {
        this.ladder = new ArrayList<>(ladderLines);
    }

    public int getHeight() {
        return ladder.size();
    }

    public int result(int index) {
        int nextIndex = index;
        for (LadderLine ladderLine : ladder) {
            nextIndex = ladderLine.move(nextIndex);
        }

        return nextIndex;
    }

    @Override
    public Iterator<LadderLine> iterator() {
        return ladder.iterator();
    }
}
