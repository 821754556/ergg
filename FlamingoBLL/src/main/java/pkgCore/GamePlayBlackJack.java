package pkgCore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import pkgException.DeckException;
import pkgException.HandException;

import pkgEnum.eGameType;

public class GamePlayBlackJack extends GamePlay {

	private Player pDealer = new Player("Dealer", 0);
	private Hand hDealer = new HandBlackJack();
	
	
	public GamePlayBlackJack(HashMap<UUID, Player> hmTablePlayers, Deck dGameDeck) {
	
		super(eGameType.BLACKJACK, hmTablePlayers, dGameDeck);	
		
		Iterator it = hmTablePlayers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Player p = (Player) pair.getValue();
			Hand h = new HandBlackJack();
			GamePlayerHand GPH = new GamePlayerHand(this.getGameID(), p.getPlayerID(), h.getHandID());
			this.putHandToGame(GPH, h);
		}
	}

	@Override
	protected Card Draw(GamePlayerHand GPH) throws DeckException, HandException {

		Card c = null;

		if (bCanPlayerDraw(GPH)) {
			Hand h = this.gethmGameHand(GPH);
			c = h.Draw(this.getdGameDeck());
			
			h.AddCard(c);
			
			this.putHandToGame(GPH, h);

		}
		return c;
	}

	private boolean bCanPlayerDraw(GamePlayerHand GPH) throws HandException {
		boolean bCanPlayerDraw = false;

		Hand h = this.gethmGameHand(GPH);

		HandScoreBlackJack HSB = (HandScoreBlackJack)h.ScoreHand();
		
		// TODO: Determine if the player can draw another card (are they busted?)

		return bCanPlayerDraw;
	}
	
	
	
	public boolean bDoesDealerHaveToDraw() throws HandException
	{
		boolean bDoesDealerHaveToDraw = true;
		
		
		HandScoreBlackJack HSB = (HandScoreBlackJack)hDealer.ScoreHand();
		
		//TODO: Determine if the dealer MUST draw.
		//		Dealer must draw unless they are bust and they don't have a 
		//		numeric score between 17-21.  
		//		5-5 = 10, they must draw
		//		A-6 = 7 or 17, they must stay (because they have 17)
		//		J-J-J = 30, they are busted, they can't draw
		
		
		return bDoesDealerHaveToDraw;
	}
	
	
	
	
	
	
	
	public void ScoreGame(GamePlayerHand GPH)
	{
		boolean bIsHandWinner = false;
		//	TODO: Determine if player is a winner
		
		//	TODO: Find the Dealer's hand
		//	TODO: Score Dealer's hand
		
		//	TODO: Find Player's hand yjjyj
		//	TODO: Score Player's hand u?
		
		//	TODO: If Player's hand > Dealer's hand and <= 21, then eBlackJackResult = WIN
		//			If Player's hand < Dealer's hand and Dealer didn't bust = LOSE
		//			If Player's hand == Dealer's hand and both didn't bust = TIE
		
		
	}

	public Player getpDealer() {
		return pDealer;
	}
}
