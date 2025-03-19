package testedeapi.com.config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Base64;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

@Value("${security.jwt.secret:}") 
    private String secret;

    @Bean
    public SecretKey secretKey() {
        if (secret == null || secret.isEmpty()) {
            SecretKey generatedKey = Jwts.SIG.HS384.key().build();
            secret = Base64.getEncoder().encodeToString(generatedKey.getEncoded());
            System.out.println("ATENÇÃO: Gerando uma nova chave temporária...");
            System.out.println(secret);
        }
        else {
            System.out.println("ATENÇÃO: Erro ao gerar chave temporária.");
        }


        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
    
}