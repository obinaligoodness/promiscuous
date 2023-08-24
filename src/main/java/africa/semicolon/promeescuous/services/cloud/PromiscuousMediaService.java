package africa.semicolon.promeescuous.services.cloud;


import africa.semicolon.promeescuous.config.AppConfig;
import africa.semicolon.promeescuous.models.Media;
import africa.semicolon.promeescuous.models.Reaction;
import africa.semicolon.promeescuous.models.User;
import africa.semicolon.promeescuous.repositories.MediaRepository;
import africa.semicolon.promeescuous.repositories.UserRepository;
import africa.semicolon.promeescuous.services.cloud.CloudService;
import africa.semicolon.promeescuous.services.cloud.MediaService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import africa.semicolon.promiscuous.dtos.response.UploadMediaResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static africa.semicolon.promeescuous.models.Reaction.DISLIKE;
import static africa.semicolon.promeescuous.models.Reaction.LIKE;

@Service
public class PromiscuousMediaService implements MediaService {
    private final CloudService cloudService;
    private final AppConfig appConfig;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;

    @Autowired
    public PromiscuousMediaService(CloudService cloudService,
                                   AppConfig appConfig,
                                   MediaRepository mediaRepository,
                                   UserRepository userRepository){
        this.cloudService = cloudService;
        this.appConfig = appConfig;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
    }
    @Override
    public UploadMediaResponse uploadMedia(MultipartFile file) {
        return uploadVideo(file);
    }

    @Override
    public UploadMediaResponse uploadProfilePicture(MultipartFile file) {
        cloudService.upload(file);
        UploadMediaResponse response = new UploadMediaResponse();
        response.setMessage("Profile picture updated");
        return response;
    }

    @Override
    public String likeOrDislike(Reaction reaction, Long userId) {
        User foundUser = userRepository.findById(userId).get();
        boolean isExistingUser = mediaRepository.existsByUser(foundUser);

        if(reaction == LIKE && !isExistingUser){
            Media media = new Media();
            media.setUser(foundUser);
            media.setLike(true);
            media.getReactions().add(LIKE);
            mediaRepository.save(media);
            return "Liked!";
        }else if(reaction == DISLIKE && isExistingUser){
            Optional<Media> media = mediaRepository.findMediaByUserAndIsLikeIsTrue(foundUser);
            Media likedMedia = media.get();
            likedMedia.setLike(false);
            likedMedia.getReactions().remove(LIKE);
            mediaRepository.save(likedMedia);
        }
        return "X";
    }

    private UploadMediaResponse uploadVideo(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary();
        Uploader uploader = cloudinary.uploader();
        try{
            Map<?,?> response =  uploader.upload(file.getBytes(), ObjectUtils.asMap(
                    "public_id","Promiscuous/Users/profile_images/"+file.getName(),
                    "api_key", appConfig.getCloudApiKey(),
                    "api_secret", appConfig.getCloudSecret(),
                    "cloud_name", appConfig.getCloudName(),
                    "secure", true,
                    "resource_type", "auto"
            ));
//            return response.get("url").toString();
            UploadMediaResponse mediaResponse = new UploadMediaResponse();
            mediaResponse.setMessage("Media upload successful");
            return mediaResponse;
        }catch (IOException exception){
            throw new RuntimeException("File upload failed: "+exception.getMessage());
        }

    }
}