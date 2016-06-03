/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.beans;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.SystemException;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.InRequestScope;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import sys.entities.Menu;

/**
 *
 * @author savin
 */
@RunWith(CdiRunner.class)
public class MenuCBeanTest {

    @Produces
    private final EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
    private EntityTransaction tm;

    private Menu menu = new Menu();

    @Inject
    private MenuCBean instance;

    public MenuCBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SystemException {
        tm=em.getTransaction();
        menu.setActiveStatus(Boolean.TRUE);
        menu.setMenuName("TestMenu");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class MenuCBean.
     */
    @Test
    @InRequestScope
    public void testFindAll() {

        System.out.println("MenuEJB.findAll");
        System.out.println("1.Menus count: " + instance.findAll().size());
        Assert.assertEquals(2, instance.findAll().size());

    }

    @Test
    @InRequestScope
    public void testCreateAndDeleteMenu() {
        System.out.println("createMenu");
        System.out.println("2.create new menu");
        tm.begin();
        instance.createMenu(menu);
        tm.commit();
        System.out.println("Menus count: " + instance.findAll().size());
        Assert.assertEquals(3, instance.findAll().size());
        System.out.println("deleteMenus");
        System.out.println("3.delete menu");
        tm.begin();
        instance.deleteMenu(menu);
        tm.commit();
        System.out.println("Menus count: " + instance.findAll().size());
        Assert.assertEquals(2, instance.findAll().size());
    }

   
//
//    /**
//     * Test of deleteMenuItems method, of class MenuCBean.
//     */
//    @Test
//    public void testDeleteMenuItems() {
//        System.out.println("deleteMenuItems");
//        MenuCBean instance = new MenuCBean();
//        instance.deleteMenuItems();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addMenuItemNMV method, of class MenuCBean.
//     */
//    @Test
//    public void testAddMenuItemNMV() {
//        System.out.println("addMenuItemNMV");
//        MenuCBean instance = new MenuCBean();
//        instance.addMenuItemNMV();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findMenuItemsNMV method, of class MenuCBean.
//     */
//    @Test
//    public void testFindMenuItemsNMV() {
//        System.out.println("findMenuItemsNMV");
//        MenuCBean instance = new MenuCBean();
//        List<MenuItem> expResult = null;
//        List<MenuItem> result = instance.findMenuItemsNMV();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
