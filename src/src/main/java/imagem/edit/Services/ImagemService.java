package imagem.edit.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.transformation.FetchLayer;
import com.cloudinary.transformation.Layer;
import com.cloudinary.transformation.TextLayer;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import imagem.edit.dto.ImagemDto;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class ImagemService {

    private static String transformarString(JsonNode value){
        return value.toString().replace('"',' ').trim();
    }
    private static String buildImage(String filters, String rotate, String crop, JsonNode resize,String gravity, String aspectRatio, String background, String opacity, String border, String radius,String fontFamily, int fontSize, String fontWeight, String textDecoration, String letterSpacing, String text, String textColor, Cloudinary cloudinary, String public_id){
        Transformation transformation = new Transformation();
        TextLayer textLayer = new TextLayer();

        if (!filters.isEmpty()){
            transformation.effect(filters);
        }
        if (!resize.get("width").isEmpty()){
            transformation.width(resize.get("width"));
        }
        if (!resize.get("height").isEmpty()){
            transformation.height(resize.get("height"));
        }
        if (!crop.isEmpty()){
            transformation.crop(crop);
        }
        if (!rotate.isEmpty()){
            transformation.angle(rotate);
        }
        if (!gravity.isEmpty()){
            transformation.gravity(gravity);
        }
        if (!aspectRatio.isEmpty()){
            transformation.aspectRatio(aspectRatio);
        }
        if (!background.isEmpty()){
            transformation.background(background);
        }
        if (!opacity.isEmpty()){
            transformation.opacity(opacity);
        }
        if (!border.isEmpty()){
            transformation.border(border);
        }
        if (!radius.isEmpty()){
            transformation.radius(radius);
        }

        transformation.chain();

        if (fontFamily.isEmpty()){
            fontFamily = "Verdana";
        }
        if (fontSize == 0){
            fontSize = 75;
        }
        if (fontWeight.isEmpty()){
            fontWeight = "normal";
        }
        if (textDecoration.isEmpty()){
            textDecoration= "none";
        }
        if (letterSpacing.isEmpty()){
            letterSpacing = "14";
        }
        if (text.isEmpty()){
            text = " ";
        }
        if (textColor.isEmpty()){
            textColor ="black";
        }
        String url = cloudinary.url().transformation(transformation
                        .color(textColor).overlay(new TextLayer().fontFamily(fontFamily).fontSize(fontSize).fontWeight(fontWeight).textDecoration(textDecoration).letterSpacing(letterSpacing).text(text)).chain()
                        .flags("layer_apply"))
                .imageTag(public_id);
        return url;
    }
    public static String cloudnaryConfig(ImagemDto data){
        JsonNode resize = data.transformations().get("resize");
        JsonNode textFormat = data.transformations().get("textFormat");
        String fontFamily = transformarString(textFormat.get("fontFamily"));
        String text = transformarString(textFormat.get("text"));
        int fontSize = Integer.parseInt(transformarString(textFormat.get("fontSize")));
        String fontWeight = transformarString(textFormat.get("fontWeight"));
        String textDecoration = transformarString(textFormat.get("textDecoration"));
        String letterSpacing = transformarString(textFormat.get("letterSpacing"));
        String textColor = transformarString(textFormat.get("textColor"));
        String link = data.nome();
        String crop = transformarString( data.transformations().get("crop"));
        String rotate = transformarString(data.transformations().get("rotate"));
        String filters = transformarString(data.transformations().get("filters"));
        String gravity = transformarString(data.transformations().get("gravity"));
        String aspectRatio = transformarString(data.transformations().get("aspectRatio"));
        String background = transformarString(data.transformations().get("background"));
        String opacity = transformarString(data.transformations().get("opacity"));
        String border = transformarString(data.transformations().get("border"));
        String radius = transformarString(data.transformations().get("radius"));

        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;
        // Upload the image
        Map params1 = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );
        try{

            String public_id = cloudinary.uploader().upload(link, params1).get("public_id").toString();
            System.out.println(public_id);
                    // Get the asset details
            Map params2 = ObjectUtils.asMap(
                    "quality_analysis", true
            );

            String url = buildImage(filters, rotate,crop, resize, gravity, aspectRatio, background, opacity, border, radius,fontFamily,fontSize, fontWeight,textDecoration, letterSpacing, text, textColor, cloudinary, public_id);

            System.out.println(
                    cloudinary.api().resource(public_id, params2));

            return url;
        }catch (Exception exception){
            return "deu ruim aqui " + exception;
        }
    }



}
