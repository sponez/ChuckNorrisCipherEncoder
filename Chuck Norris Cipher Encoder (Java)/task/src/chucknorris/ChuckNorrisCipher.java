package chucknorris;

public class ChuckNorrisCipher {
    private static class Encoder {
        private static String encodeBinaryString(String binaryString) {
            StringBuilder encoderResult = new StringBuilder();

            for (int i = 0; i < binaryString.length();) {
                char currentBit = binaryString.charAt(i);

                if (currentBit == '1') {
                    encoderResult.append("0 ");
                } else {
                    encoderResult.append("00 ");
                }

                while (i < binaryString.length() && binaryString.charAt(i) == currentBit) {
                    encoderResult.append(0);
                    ++i;
                }

                encoderResult.append(' ');
            }

            return encoderResult.deleteCharAt(encoderResult.length() - 1).toString();
        }

        private static String convertBinaryStringInto7bitFormat(String binaryString) {
            return String.format("%7s", binaryString).replace(' ', '0');
        }

        private static String convertStringIntoBinaryString(String input) {
            StringBuilder encoderResult = new StringBuilder();

            for (int i = 0; i < input.length(); ++i) {
                encoderResult.append(
                    convertBinaryStringInto7bitFormat(
                        Integer.toBinaryString(
                            input.charAt(i)
                        )
                    )
                );
            }

            return encoderResult.toString();
        }
    }

    private static class Decoder {
        private static String convertBinaryStringIntoString(String binaryString) {
            StringBuilder decoderResult = new StringBuilder();

            for (int i = 0, currentChar = 0; i < binaryString.length(); ++i) {
                if (binaryString.charAt(i) == '1') {
                    int currentPower = 6 - i % 7;
                    currentChar += (int) Math.pow(2, currentPower);
                }

                if (i % 7 == 6) {
                    decoderResult.append((char) currentChar);
                    currentChar = 0;
                }
            }

            return decoderResult.toString();
        }

        private static boolean isZerosString(String string) {
            for (int i = 0; i < string.length(); ++i) {
                if (string.charAt(i) != '0') {
                    return false;
                }
            }

            return true;
        }

        private static String decodeCipherIntoBinaryString(String cipher) throws IllegalArgumentException {
            StringBuilder decoderResult = new StringBuilder();
            String[] consequencesArray = cipher.split(" ");

            if (consequencesArray.length % 2 != 0) {
                throw new IllegalArgumentException("Encoded string is not valid.");
            }

            for (int i = 0; i < consequencesArray.length; i += 2) {
                if (!isZerosString(consequencesArray[i + 1])) {
                    throw new IllegalArgumentException("Encoded string is not valid.");
                }

                if (consequencesArray[i].equals("0")) {
                    decoderResult.append("1".repeat(consequencesArray[i + 1].length()));
                } else if (consequencesArray[i].equals("00")) {
                    decoderResult.append("0".repeat(consequencesArray[i + 1].length()));
                } else {
                    throw new IllegalArgumentException("Encoded string is not valid.");
                }
            }

            if (decoderResult.length() % 7 != 0) {
                throw new IllegalArgumentException("Encoded string is not valid.");
            }

            return decoderResult.toString();
        }
    }


    public static String encode(String input) {
        return Encoder.encodeBinaryString(
                Encoder.convertStringIntoBinaryString(input)
            );
    }

    public static String decode(String input) throws IllegalArgumentException {
        return input.isEmpty() ? "" :
            Decoder.convertBinaryStringIntoString(
                Decoder.decodeCipherIntoBinaryString(input)
            );
    }
}
