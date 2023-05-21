package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {

    @DataProvider
    public Iterator<Object[]> loginModelDto() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder()
                .email("abc@def.com")
                .password("$Abcdef12345")
                .build()
        });
        list.add(new Object[]{User.builder()
                .email("abc@def.com")
                .password("$Abcdef12345")
                .build()
        });
        list.add(new Object[]{User.builder()
                .email("abc@def.com")
                .password("$Abcdef12345")
                .build()
        });

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> regPositiveTestDtoCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader
                (new FileReader(new File("src/test/resources/dataRegistrationTestsPositive.csv")));
        String line = reader.readLine();
        while (line!=null) {
            String[] splitLine = line.split(",");
            list.add(new Object[]{User.builder()
                    .email(splitLine[0])
                    .password(splitLine[1])
                    .build()
            });
            line = reader.readLine();
        }
        return list.iterator();
    }
}
