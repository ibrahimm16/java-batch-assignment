import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCacheSwing {

    final int CACHE_SIZE = 10;
    // UI
    boolean running = true;
    Display display;
    Map<Character, Boolean> keysPressed;
    // LRUCache
    LinkedList<Character> keyList;
    Map<Character, Integer> keyMap;

    LRUCacheSwing() {
        display = new Display();
        keyList = new LinkedList<>();
        keyMap = new HashMap<>();
    }

    public static void main(String[] args) {
        LRUCacheSwing lruCache = new LRUCacheSwing();
        lruCache.start();
    }

    void start() {
        while (running) {
            handleInput();
            display.render();
        }
        System.exit(0);
    }

    void handleInput() {
        // Iterates through the key value pairs of the keysPressed map which is maintained by the KeyListener
        keysPressed.forEach((k, v) -> {
            // If the key is pressed, updates it in the keyList and keyMap appropriately according to the LRUCache algorithm
            if (v) {
                // Updates the keyList and keyMap with the pressed key and the number of times it's been updated
                keyList.remove(k);
                keyList.add(k);
                keyMap.put(k, keyMap.getOrDefault(k, 0) + 1);
                // If the list is at capacity, removes the oldest element from the keyList and keyMap
                if (keyList.size() > CACHE_SIZE) {
                    keyMap.remove(keyList.get(0));
                    keyList.remove(0);
                }
            }
        });
    }

    // Helper Display class for a UI with key input
    class Display implements KeyListener {

        JFrame frame;
        Canvas canvas;
        BufferStrategy bufferStrategy;

        Display() {
            Dimension dimension = new Dimension(400, 300);
            canvas = new Canvas();
            canvas.setSize(dimension);
            canvas.setFocusable(false);

            frame = new JFrame("LRUCache Swing App");
            frame.addKeyListener(this);
            frame.setSize(dimension);
            frame.add(canvas);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            keysPressed = new HashMap<>();
        }

        void render() {
            bufferStrategy = canvas.getBufferStrategy();
            if (bufferStrategy == null) {
                canvas.createBufferStrategy(3);
                return;
            }

            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            g.setColor(Color.black);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

            // Iterates through the characters in the keyList
            // Prints each character with the number of times it's been held while rendering
            int y = -5;
            for (Character key : keyList) {
                Integer value = keyMap.get(key);
                String mapString = "Key " + key + " | Renders With Key Held " + value;
                g.drawString(mapString, 5, (y += 20));
            }

            g.drawString("Example of an LRUCache with a cache size of 10 characters", 5, 250);
            g.drawString("Begin pressing keys to populate the cache", 5, 270);
            g.drawString("Terminate with 0", 5, 290);

            bufferStrategy.show();
            g.dispose();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // Terminates if zero is pressed
            if (e.getKeyCode() == KeyEvent.VK_0) {
                running = false;
            }

            // Puts the pressed character with a value of true in the keysPressed map
            keysPressed.put(e.getKeyChar(), true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Puts the released character with a value of false in the keysPressed map
            keysPressed.put(e.getKeyChar(), false);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }
}
