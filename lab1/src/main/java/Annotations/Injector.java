package Annotations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repositories.Repository;
import sorters.ISorter;
import utilities.Configurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Properties;

public class Injector {

    private static final Logger LOGGER = LogManager.getLogger(Injector.class);
    private static final String PATH = "/config.properties";
    public <T extends Repository> T inject(T repository) throws InstantiationException, IOException, ClassNotFoundException, IllegalAccessException {
        Class<?> clazz = repository.getClass();
        Field[] fields = clazz.getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                    String sortProperty = "";
                    URL url = getClass().getResource(PATH);
                    Properties properties = new Properties();
                    properties.load(new FileInputStream(new File(url.getPath())));
                    sortProperty=properties.getProperty("sorters.ISorter");
                    Class name = Class.forName(sortProperty);

                    Object sorter = name.newInstance();


                    field.set(repository, sorter);



            }
        }
        return repository;
    }
}
