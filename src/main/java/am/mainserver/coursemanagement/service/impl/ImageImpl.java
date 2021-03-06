package am.mainserver.coursemanagement.service.impl;

import am.mainserver.coursemanagement.config.HibernateUtilConfig;
import am.mainserver.coursemanagement.domain.Image;
import am.mainserver.coursemanagement.dto.ImageDto;
import am.mainserver.coursemanagement.repository.ImageRepository;
import am.mainserver.coursemanagement.service.ImageService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


@Service
public class ImageImpl implements ImageService {


    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private HibernateUtilConfig hibernateUtilConfig;

    public  String encodeToString(File file) {

        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BufferedImage bufferedImage;


        try {
            bufferedImage = ImageIO.read(file);
            ImageIO.write(bufferedImage,".jpg", bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public  BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void storeImage(final ImageDto imageDto) {
        if (imageDto == null){
            System.out.println("Choose imageUrl");
            //there will be exception
        }else {

            final Image image = new Image();
            image.setImageUrl(imageDto.getImageUrl());
            image.setUser(imageDto.getUser());
            imageRepository.save(image);
        }

    }

    @Override
    public Image getImageByUserId(Long userId) {
        Session session = hibernateUtilConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Image where user_id = :userId");
        query.setParameter("userId",userId);
        Image image = (Image) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return image;
    }

    @Override
    public Image getImageByCourseId(Long courseId) {
        Session session = hibernateUtilConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Image where course_id = :courseId");
        query.setParameter("courseId",courseId);
        Image image = (Image) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return image;
    }

    @Override
    public void deleteUserImage(Long userId) {
        Image image = getImageByUserId(userId);
        imageRepository.delete(image.getId());
    }




}
