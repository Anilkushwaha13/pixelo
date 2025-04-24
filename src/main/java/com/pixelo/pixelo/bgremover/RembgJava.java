package com.pixelo.pixelo.bgremover;
import ai.onnxruntime.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.nio.FloatBuffer;
import java.util.Collections;

public class RembgJava {

//    public static void main(String[] args) throws Exception {
//        String inputImagePath = "C:\\Users\\kushw\\OneDrive\\Desktop\\frontend2\\image-editor\\public\\img\\ai-image.jpg";
//        String outputImagePath = "C:/Users/kushw/OneDrive/Desktop/Output/output.png";
//        String modelPath = "D:/pixelo/src/main/resources/u2net.onnx";
//        // Load and resize input image
//        BufferedImage inputImage = ImageIO.read(new File(inputImagePath));
//        BufferedImage resized = resizeTo320x320(inputImage); // Resize to 320x320
//        float[] inputTensorData = convertImageToTensor(resized); // Convert to NCHW float[]
//
//        try (OrtEnvironment env = OrtEnvironment.getEnvironment();
//             OrtSession session = env.createSession(modelPath, new OrtSession.SessionOptions())) {
//
//            long[] shape = new long[]{1, 3, 320, 320}; // NCHW
//            OnnxTensor inputTensor = OnnxTensor.createTensor(env, FloatBuffer.wrap(inputTensorData), shape);
//
//            // Use the correct input name as per the model's definition
//            OrtSession.Result result = session.run(Collections.singletonMap("input.1", inputTensor)); // Updated to "input.1"
//
//            // Get output mask (4D array)
//            float[][][][] output = (float[][][][]) result.get(0).getValue(); // 4D output
//
//            // Extract the 2D mask (320x320) from the 4D array
//            float[][] reshapedMask = output[0][0];
////            float[][] reshapedScaledMask = sclaedMask(reshapedMask,inputImage.getHeight(),inputImage.getWidth());
//            // Access first channel (only one channel in U-2-Net)
//
//            // Apply alpha mask to image
//            BufferedImage finalImage = applyAlphaMask(inputImage, sclaedMask(reshapedMask,inputImage.getHeight(),inputImage.getWidth()));
//            ImageIO.write(finalImage, "png", new File(outputImagePath));
//            System.out.println("Background removed and saved to " + outputImagePath);
//        }
//    }


    public static BufferedImage getBgRemoved( BufferedImage inputImage) throws  Exception{
        String modelPath = "/src/main/resources/u2net.onnx";
        BufferedImage resized = resizeTo320x320(inputImage); // Resize to 320x320
        float[] inputTensorData = convertImageToTensor(resized); // Convert to NCHW float[]

        try (OrtEnvironment env = OrtEnvironment.getEnvironment();
             OrtSession session = env.createSession(modelPath, new OrtSession.SessionOptions())) {

            long[] shape = new long[]{1, 3, 320, 320}; // NCHW
            OnnxTensor inputTensor = OnnxTensor.createTensor(env, FloatBuffer.wrap(inputTensorData), shape);

            // Use the correct input name as per the model's definition
            OrtSession.Result result = session.run(Collections.singletonMap("input.1", inputTensor)); // Updated to "input.1"

            // Get output mask (4D array)
            float[][][][] output = (float[][][][]) result.get(0).getValue(); // 4D output

            // Extract the 2D mask (320x320) from the 4D array
            float[][] reshapedMask = output[0][0];
            // Access first channel (only one channel in U-2-Net)

            // Apply alpha mask to image
            BufferedImage finalImage = applyAlphaMask(inputImage, sclaedMask(reshapedMask,inputImage.getHeight(),inputImage.getWidth()));
            return finalImage;
        }
    }
private static float[][] sclaedMask(float[][] reshapedMask, int originHeight, int originWidth) {
    int height = reshapedMask.length;
    int width = reshapedMask[0].length;


    float[][] scaledMask = new float[originHeight][originWidth];

    for (int i = 0; i < originHeight; i++) {
        for (int j = 0; j < originWidth; j++) {
            int x = i * height / originHeight;
            int y = j * width / originWidth;

            scaledMask[i][j] = reshapedMask[x][y];

        }
    }
    return scaledMask;
}
    // Resize image to 320x320
   private static BufferedImage resizeTo320x320(BufferedImage originalImage) {
        Image tmp = originalImage.getScaledInstance(320, 320, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    // Convert BufferedImage to float array in NCHW format
    private static float[] convertImageToTensor(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        float[] tensor = new float[3 * height * width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                tensor[0 * height * width + y * width + x] = r / 255.0f;
                tensor[1 * height * width + y * width + x] = g / 255.0f;
                tensor[2 * height * width + y * width + x] = b / 255.0f;
            }
        }

        return tensor;
    }

    // Reshape 1D array into a 2D array (height x width)
//    public static float[][] reshapeMask(float[] mask, int height, int width) {
//        float[][] reshapedMask = new float[height][width];
//        int index = 0;
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                reshapedMask[i][j] = mask[index++];
//            }
//        }
//        return reshapedMask;
//    }

    // Apply alpha mask to image and return new image
    private static BufferedImage applyAlphaMask(BufferedImage image, float[][] mask) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Mask value is between 0-1, apply as alpha
                int alpha = (int) (clamp(mask[y][x]) * 255);
                int rgba = (alpha << 24) | (r << 16) | (g << 8) | b;
                result.setRGB(x, y, rgba);
            }
        }
        return result;
    }

    private static float clamp(float value) {
        return Math.max(0, Math.min(1, value));
    }
}
