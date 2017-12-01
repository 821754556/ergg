 package pkgCore;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.UUID;

import org.junit.Test;

import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.HandException;

public class GamePlayBlackJackTest {

	@Test
	public void TESTPlayerWin() {
		Player Player = new Player("JACK", 0);
		HashMap<UUID, Player> HMTablePlayers = new HashMap<>();
		HMTablePlayers.put(Player.getPlayerID(),Player );
		
		Deck DECK = new Deck();
		GamePlayBlackJack game = new GamePlayBlackJack(HMTablePlayers, DECK);
		Hand HAND = new HandBlackJack();
		HAND.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		HAND.AddCard(new Card(eSuit.SPADES, eRank.FOUR));
		HAND.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		GamePlayerHand gamePlayerHand=new GamePlayerHand(game.getGameID(), Player.getPlayerID(), HAND.getHandID());
		game.putHandToGame(gamePlayerHand, HAND);
		
		Player DEALER = game.getpDealer();
		Hand handtwo = new HandBlackJack();
		handtwo.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));
		handtwo.AddCard(new Card(eSuit.SPADES, eRank.THREE));
		handtwo.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		GamePlayerHand gamePlayerHandtwo=new GamePlayerHand(game.getGameID(), DEALER.getPlayerID(), handtwo.getHandID());
		game.putHandToGame(gamePlayerHandtwo, handtwo);
		
		game.ScoreGame(gamePlayerHand);
		
		assertTrue(HAND.isbWinner());
	}

	@Test
	public void TestPlayerLosing() {
		Player PLAYER = new Player("JC", 0);
		HashMap<UUID, Player> HMTablePlayers = new HashMap<>();
		HMTablePlayers.put(PLAYER.getPlayerID(),PLAYER );
		
		Deck DECK = new Deck();
		GamePlayBlackJack game = new GamePlayBlackJack(HMTablePlayers, DECK);
		Hand HAND = new HandBlackJack();
		HAND.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));
		HAND.AddCard(new Card(eSuit.SPADES, eRank.TEN));
		HAND.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		GamePlayerHand gamePlayerHand=new GamePlayerHand(game.getGameID(), PLAYER.getPlayerID(), HAND.getHandID());
		game.putHandToGame(gamePlayerHand, HAND);
		
		Player DEALER = game.getpDealer();
		Hand handtwo = new HandBlackJack();
		handtwo.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));
		handtwo.AddCard(new Card(eSuit.SPADES, eRank.THREE));
		handtwo.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		GamePlayerHand gamePlayerHand2=new GamePlayerHand(game.getGameID(), DEALER.getPlayerID(), handtwo.getHandID());
		game.putHandToGame(gamePlayerHand2, handtwo);
		
		game.ScoreGame(gamePlayerHand);
		
		assertFalse(HAND.isbWinner());
	}
	
	@Test
	public void TestPlayerPush() {
		Player player = new Player("JC", 0);
		HashMap<UUID, Player> HMTablePlayers = new HashMap<>();
		HMTablePlayers.put(player.getPlayerID(),player );
		
		Deck deck = new Deck();
		GamePlayBlackJack game = new GamePlayBlackJack(HMTablePlayers, deck);
		Hand hand = new HandBlackJack();
		hand.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));
		hand.AddCard(new Card(eSuit.SPADES, eRank.TEN));
		hand.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		GamePlayerHand gamePlayerHand=new GamePlayerHand(game.getGameID(), player.getPlayerID(), hand.getHandID());
		game.putHandToGame(gamePlayerHand, hand);
		
		try {
			assertTrue(game.bDoesDealerHaveToDraw());
		} catch (HandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestTwoPlayersWinning()
	{
		Player player = new Player("JACK", 0);
		Player player2 = new Player("FC", 1);
		HashMap<UUID, Player> HMTablePlayers = new HashMap<>();
		HMTablePlayers.put(player.getPlayerID(),player );
		HMTablePlayers.put(player2.getPlayerID(), player2);
		
		Deck deck = new Deck();
		GamePlayBlackJack game = new GamePlayBlackJack(HMTablePlayers, deck);
		Hand hand = new HandBlackJack();
		hand.AddCard(new Card(eSuit.CLUBS, eRank.FOUR));
		hand.AddCard(new Card(eSuit.SPADES, eRank.SEVEN));
		hand.AddCard(new Card(eSuit.CLUBS, eRank.TEN));
		GamePlayerHand gamePlayerHand=new GamePlayerHand(game.getGameID(), player.getPlayerID(), hand.getHandID());
		game.putHandToGame(gamePlayerHand, hand);
		
		Hand hand3 = new HandBlackJack();
		hand3.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand3.AddCard(new Card(eSuit.SPADES, eRank.FOUR));
		hand3.AddCard(new Card(eSuit.CLUBS, eRank.TEN));
		GamePlayerHand gamePlayerHand3=new GamePlayerHand(game.getGameID(), player2.getPlayerID(), hand3.getHandID());
		game.putHandToGame(gamePlayerHand3, hand3);
		
		Player dealer = game.getpDealer();
		Hand hand2 = new HandBlackJack();
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand2.AddCard(new Card(eSuit.SPADES, eRank.THREE));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		GamePlayerHand gamePlayerHand2=new GamePlayerHand(game.getGameID(), dealer.getPlayerID(), hand2.getHandID());
		game.putHandToGame(gamePlayerHand2, hand2);
		
		game.ScoreGame(gamePlayerHand);
		
		game.ScoreGame(gamePlayerHand3);
		
		assertTrue(hand.isbWinner());
		
		assertTrue(hand3.isbWinner());
	}
}