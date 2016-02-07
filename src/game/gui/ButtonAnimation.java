package game.gui;

import javafx.animation.AnimationTimer;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ButtonAnimation
{
    public static class Frame
    {
        public Background image;

        public long time;

        public Frame(Background i, long t)
        {
            image = i;
            time = t;
        }
    }

    private SimpleObjectProperty<Background> currentImg;

    private AnimationTimer animationTimer;

    private Frame[] timeline;

    private int currentFrame;

    private long lastChange;

    public ButtonAnimation(Frame... frames)
    {
        if (frames == null || frames.length == 0)
        {
            frames = new Frame[1];
            // frames[0] = new Frame(new Background(new
            // BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(0))),
            // 2500000000l);
            // 2,5 Sekunde?
            frames[0] = new Frame(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(0), new Insets(0))), 2500000000l);
        }
        timeline = frames;
        currentImg = new SimpleObjectProperty<Background>(frames[0].image);
        currentFrame = 0;
        lastChange = System.nanoTime();
        animationTimer = new AnimationTimer()
        {

            @Override
            public void handle(long now)
            {
                if ((now - lastChange) > timeline[currentFrame].time)
                {
                    lastChange = lastChange + timeline[currentFrame].time;
                    currentFrame = (currentFrame + 1) % timeline.length;
                    currentImg.set(timeline[currentFrame].image);
                }
            }
        };
        /// TODO nicht immer sofort starten
        animationTimer.start();
    }

    public void start()
    {
        animationTimer.start();
    }

    public void stop()
    {
        animationTimer.stop();
    }

    public ReadOnlyObjectProperty<Background> currentImage()
    {
        return currentImg;
    }

}
