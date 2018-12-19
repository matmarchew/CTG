package communication.information;

import communication.CustomJSONObject;
import rules.Messages;
import rules.players.Player;

public class PutBetCardInformationAction implements InformationAction {
    private final Player player;

    public PutBetCardInformationAction(Player player) {
        this.player = player;
    }

    @Override
    public void sendInformation() {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.BET_CARD, player.getUsedBetCards());
        player.sendMessageToMobile(json.toString());
    }
}
