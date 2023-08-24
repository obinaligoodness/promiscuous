package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.config.AppConfig;
import africa.semicolon.promeescuous.services.cloud.CloudService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryServiceImpl implements CloudService {
    private final AppConfig appConfig;
    @Override
    public String upload(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary();
        Uploader uploader = cloudinary.uploader();
        try{
            Map<?,?> response = uploader.upload(file.getBytes(), ObjectUtils.asMap(
                    "public_id","promiscous/users/profile_images/"+file.getName(),
                    "api_key",appConfig.getCloudApiKey(),
                    "api_secret",appConfig.getCloudSecret(),
                    "cloud_name", appConfig.getCloudName(),
                    "secure",true
            ));

            return response.get("url").toString();
        }
        catch (IOException exception){
            throw new RuntimeException("File upload failed");
        }
    }

    public String uploadVideo(MultipartFile file){
            Cloudinary cloudinary = new Cloudinary();
            Uploader uploader = cloudinary.uploader();
            try{
                Map<?,?> response = uploader.upload(file.getBytes(), ObjectUtils.asMap("resource_type", "video",
                        "public_id","promiscous/users/profile_images/"+file.getName(),
                        "api_key",appConfig.getCloudApiKey(),
                        "api_secret",appConfig.getCloudSecret(),
                        "cloud_name", appConfig.getCloudName(),
                        "secure",true
                ));

                return response.get("url").toString();
            }
            catch (IOException exception){
                throw new RuntimeException("File upload failed");
            }
        }

}
