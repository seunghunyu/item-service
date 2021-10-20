package hello.itemservice.converter;

import hello.itemservice.typeconverter.converter.IntegerToStringConverter;
import hello.itemservice.typeconverter.converter.IpToStringPortConverter;
import hello.itemservice.typeconverter.converter.StringToIntegerConverter;
import hello.itemservice.typeconverter.converter.StringToIpPortConverter;
import hello.itemservice.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    @Test
    void stringToInteger(){
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString(){
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @Test
    void stringToIpPort(){
        IpToStringPortConverter converter = new IpToStringPortConverter();
        IpPort source = new IpPort("127.0.0.1",8080);
        String result = converter.convert(source);
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void IpPortToString(){
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1",8080));
    }
}
