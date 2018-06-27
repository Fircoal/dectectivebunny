package fluffybunny.dectectivebunny;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fluffybunny.dectectivebunny.config.HibernateConf;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConf.class);
    	   
    	DectectiveRunner drun = ctx.getBean(DectectiveRunner.class);
        drun.logAnimeInfo();
    }
}
