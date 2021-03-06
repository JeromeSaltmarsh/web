package com.market.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.jdbc.Blob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private byte[] bytes;

    @Column
    private Blob blob;

    @Column
    @Getter
    @Setter
    private String base64;


    public Image(byte[] bytes) {
        this.bytes = bytes;

        this.base64 = Base64.getMimeEncoder().encodeToString(bytes);
    }

    public Image(String base61Encoded) throws IOException {
        base61Encoded = base61Encoded.replace("\\", "");
        this.base64 = base61Encoded;
        bytes = Base64.getMimeDecoder().decode(base61Encoded);
    }

    @JsonIgnore
    public BufferedImage getBufferedImage() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        inputStream.close();
        return bufferedImage;
    }
}
