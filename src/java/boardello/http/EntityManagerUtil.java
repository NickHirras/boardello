/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boardello.http;

import javax.persistence.EntityManager;
/**

 *

 * @author Prasobh.K

 */

public class EntityManagerUtil {

	public static final ThreadLocal<EntityManager> ENTITY_MANAGERS = new ThreadLocal<EntityManager>();

	/**

	 * Returns a fresh EntityManager

	 */

	public static EntityManager getEntityManager() {

		return ENTITY_MANAGERS.get();

	}

}