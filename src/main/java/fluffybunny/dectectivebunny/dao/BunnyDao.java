package fluffybunny.dectectivebunny.dao;

import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import fluffybunny.dectectivebunny.entity.Anime;
import fluffybunny.dectectivebunny.entity.AnimeRequest;

@Repository("BunnyDao")
// @Scope("singleton")
@Transactional
public class BunnyDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<AnimeRequest> getAnimeRequests(){
		Query query = sessionFactory.getCurrentSession().createQuery(" from AnimeRequest");
		List<AnimeRequest> requests = query.getResultList();
		return requests;
	}

	public void updateAnime(Anime anime) {
		sessionFactory.getCurrentSession().saveOrUpdate(anime);
	}
	
	public Anime getAnimeByName(String name) {
		System.out.println(name);
		Query query = sessionFactory.getCurrentSession().createQuery(" from Anime where name=:name");
		List<Anime> entries = query.setParameter("name", name).getResultList();
		if(entries.size() == 0) {
			return null;
		}
		return entries.get(0);
	}

	public Anime getAnimeById(int id) {
		Query query = sessionFactory.getCurrentSession().createQuery(" from Anime where id=" + id);
		List<Anime> entries = query.getResultList();
		if(entries.size() == 0) {
			Anime ani = new Anime();
			ani.setName("Not currently loaded.");
			ani.setId(id);
			return ani;
		}
		Anime ani = entries.get(0);
		System.out.println(ani.getGenres());
		System.out.println(ani.getProducers());
		System.out.println(ani.getStudio());
		System.out.println(ani.getScorePers());
		//sessionFactory.getCurrentSession().merge(entries.get(0));
		return ani;
	}

	public void removeCompletedRequest(AnimeRequest currReq) {
		sessionFactory.getCurrentSession().delete(currReq);
	}


	/*
	 * @Autowired
	 * 
	 * @Qualifier("malbunnySessionFactory") public void
	 * setmalbunnySessionFactory(SessionFactory sessionFactory){ this.sessionFactory
	 * = sessionFactory; }
	 */

	/*
	 * public String authUser(String username, String password) { String role = "";
	 * List<User> list = (List<User>) super.getHibernateTemplate().
	 * find("from User where username=? and password=? ", username, password);
	 * 
	 * if(list.isEmpty()){ role=""; }else{ role="user"; } return role; }
	 * 
	 * public String register(User entity) { System.out.println("dao");
	 * System.out.println(entity); try{ super.getHibernateTemplate().save(entity); }
	 * catch(Exception e) { System.out.println(e.getMessage());
	 * System.out.println(e.getStackTrace()); return "failure"; } return "success";
	 * }
	 * 
	 * public User loadUser(String username) { System.out.println(username);
	 * List<User> list = (List<User>)
	 * super.getHibernateTemplate().find("from User where username=?", username);
	 * if(list.isEmpty()){ return null; }else{ return list.get(0); } }
	 * 
	 * public int createBattle(BattleEntity entity) { System.out.println(entity);
	 * try{ super.getHibernateTemplate().save(entity); } catch(Exception e) {
	 * System.out.println(e.getMessage()); System.out.println(e.getStackTrace());
	 * return 0; } return entity.getId(); }
	 * 
	 * public BattleEntity getBattle(int bid) { List<BattleEntity> list =
	 * (List<BattleEntity>)
	 * super.getHibernateTemplate().find("from BattleEntity where id=?", bid);
	 * return list.get(0); }
	 * 
	 * public List<ScoreEntity> getScores(int bid) { return (List<ScoreEntity>)
	 * super.getHibernateTemplate().find("from ScoreEntity where bid=?", bid); }
	 * 
	 * public void saveRelation(ScoreEntity score) {
	 * super.getHibernateTemplate().save(score); }
	 * 
	 * public List<BattleEntity> getUnfinishedBattles() { return
	 * (List<BattleEntity>)
	 * super.getHibernateTemplate().find("from BattleEntity where ended=false"); }
	 * 
	 * public void updateBattle(int bid, String user) { List<BattleEntity> battles =
	 * (List<BattleEntity>)
	 * super.getHibernateTemplate().find("from BattleEntity where id=?",bid);
	 * BattleEntity battle = battles.get(0); battle.setWinner(user);
	 * battle.setEnded(true); super.getHibernateTemplate().merge(battle);
	 * 
	 * }
	 * 
	 * public void saveScore(int bid, String player, Integer integer) {
	 * System.out.println(bid + " " + player + " " + integer); List<ScoreEntity>
	 * scores = (List<ScoreEntity>) super.getHibernateTemplate().
	 * find("from ScoreEntity where bid=? and writerName=?", bid, player);
	 * ScoreEntity score = scores.get(0); score.setWords(integer);
	 * super.getHibernateTemplate().merge(score); }
	 */

}
