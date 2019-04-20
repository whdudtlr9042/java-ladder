import domain.*;
import util.Console;
import view.InputView;
import view.ResultView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LadderApplication {
    public static void main(String[] args) {
        try {
            List<String> inputPlayer = Arrays.asList(InputView.inputPalyer().split(","));
            Players players = new Players(inputPlayer);
            int countOfPerson = players.countOfPerson();
            Result result = new Result(InputView.inputResult(), countOfPerson);
            Level level = Level.fineLevel(InputView.inputLever());

            Ladder ladder = new Ladder(countOfPerson, level);

            ResultView.printLadderBoard(players, ladder, result);
            String player = InputView.inputWho();

            Map<String, String> map = new HashMap<>();
            inputPlayer.forEach(playerName -> {
                Match match = new Match(players, playerName);
                int playerPoint = match.calcPlayerPoint();
                int resultIndex = ladder.calcStartIndex(playerPoint);
                map.put(playerName, result.getResult(resultIndex));
            });

            if ("all".equals(player)) {
                ResultView.allResult(map);
            } else {
                ResultView.result(player, map.get(player));
            }

        } catch (Exception e) {
            Console.print(e.getMessage());
        }
    }
}