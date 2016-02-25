package asset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import asset.AssetManager.ImageResource;
import asset.AssetManager.ResourceType;
import game.gui.ButtonAnimation;

public class ButtonAnimationResource extends ResourceType
{
    public ButtonAnimationResource(String p)
    {
        super(p);
        ArrayList<ButtonAnimation.Frame> frames = new ArrayList<ButtonAnimation.Frame>();

        String path = null;
        String time = null;
        BufferedReader br = null;
        FileReader fr = null;
        try
        {
            fr = new FileReader(p);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null)
            {
                path = line.substring(0, line.indexOf(','));
                time = line.substring(line.indexOf(',') + 1);
                ImageResource imgRes = new ImageResource(path);
                frames.add(new ButtonAnimation.Frame(imgRes, (long) (Double.valueOf(time) * 1000000000l)));
            }
            ButtonAnimation.Frame[] fa = new ButtonAnimation.Frame[frames.size()];
            resource = new ButtonAnimation(frames.toArray(fa));
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Teil der Aninmation kann nicht geladen werden " + path);
            e.printStackTrace();
        }
        catch (FileNotFoundException e1)
        {
            System.out.println("Resource not found " + p);
            e1.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("Error trying to read Resource " + p);
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    public ButtonAnimation getButtonAnimation()
    {
        return (ButtonAnimation) resource;
    }
}
