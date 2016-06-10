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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import sys.entities.Menu;
import sys.entities.MenuItem;
import sys.entities.View;

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
    private View view;

    @Inject
    private FindMenuSession fms;
    @Inject
    private MenuCBean instance;
    @Inject
    private MenuItemCBean instance2;
    @Inject
    private OpenMenuView omv;
    @Inject
    private ViewCBean viewClient;

//    public MenuCBeanTest() {
//    }
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SystemException {
        tm = em.getTransaction();

//        menu.setActiveStatus(Boolean.TRUE);
//        menu.setMenuName("TestMenu");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class MenuCBean.
     */
//    @Test
//    @InRequestScope
//    public void testFindAll() {
//
//        System.out.println("MenuEJB.findAll");
//        System.out.println("1.Menus count: " + instance.findAll().size());
//        Assert.assertEquals(3, instance.findAll().size());
//
//    }
    private void getMenuItems() {
        for (MenuItem mi : menu.getMenuItems()) {
            System.out.println("id=" + mi.getMenuItem_id() + "; name=" + mi.getMenuItem());
        }
    }

    @InRequestScope
    public void testAddMenuItemOMV2() {

        System.out.println("Добавляем punkt2:");
        omv.setNewView(view);
        omv.setNewMenuName("punkt2");
        tm.begin();
        instance.addMenuItemOMV();
        tm.commit();
        getMenuItems();
    }

    @Test
    @InRequestScope
    public void testAddMenuItemOMV() {
        fms.setMenu_id(157l);
        menu = instance.findMenu(157l);

        System.out.println("MenuName=" + menu.getMenuName());
        getMenuItems();
        System.out.println("Добавляем punkt1:");
        view = viewClient.findViewById(1l);
        omv.setNewView(view);
        omv.setNewMenuName("punkt1");
        tm.begin();
        instance.addMenuItemOMV();
        tm.commit();
        getMenuItems();

        testAddMenuItemOMV2();

    }

//    @Test
//    @InRequestScope
//    public void testCreateDeleteMenuItems() {
//
//        System.out.println("---==createMenuItems==---");
//        tm.begin()m;
//        instance.createMenu(menu);
//        tm.commit();
//        Assert.assertEquals(3, instance.findAll().size());
//
//        //Добавим первую строку
//        MenuItem mi = new MenuItem();
//        mi.setView_id(1l);
//        mi.setLine_num(Short.parseShort("1"));
//        mi.setMenuItem("Testik1");
//        tm.begin();
//        instance.addMenuItemNMV(menu, mi);
//        tm.commit();
//
//        //Добавим вторую строку
//        MenuItem mi2 = new MenuItem();
//        mi2.setView_id(1l);
//        mi2.setLine_num(Short.parseShort("2"));
//        mi2.setMenuItem("Testik2");
//        tm.begin();
//        instance.addMenuItemNMV(menu, mi2);
//        tm.commit();
//        Assert.assertEquals(6, instance2.findAll().size());
//
//        //Удалим меню
//        tm.begin();
//        instance.deleteMenu(menu);
//        tm.commit();
//        Assert.assertEquals(2, instance.findAll().size());
//
//    }
}
