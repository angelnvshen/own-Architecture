package com.lawcloud.lawper.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * This class provides some command static methods that will be used for string
 * manipulation.
 */
public class StringUtil
{
    /**
     * The default number of characters to search (to the left of the desired
     * fold point) for a whitespace character to fold the string at.
     */
    public static final int DEFAULT_FOLD_WINDOW = 15;

    /**
     * The default fold width (in number of characters)
     */
    public static final int DEFAULT_FOLD_WIDTH = 80;

    /**
     * Represents a failed index search.
     */
    public static final int INDEX_NOT_FOUND = -1;

    private static final String LS = System.getProperty ("line.separator");

    // All methods are static, this shouldn't be instantiated
    private StringUtil ()
    {
    }

    /**
     * Returns the requested field of a string separated by the specified token.
     * 
     * @param src Source String
     * @param delim Delimiter separating fields.
     * @param field Field to return.
     * @return Specific field.
     */
    public static String getField (String src, String delim, int field)
    {
        StringTokenizer m_tokenizer = new StringTokenizer (src, delim);
        int count = 0;
        while (m_tokenizer.hasMoreElements ())
        {
            count++;
            if (field == count)
            {
                return m_tokenizer.nextToken ().trim ();
            }
            else
            {
                m_tokenizer.nextToken ();
            }
        }
        return null;
    }

    /**
     * Counts the number of fields in a string. If two delimiters follow one
     * another, they are not considered a field. If the string starts with, or
     * ends with a delimiter it is not considered a field. For example, this
     * method reports 6 fields when using "|" as a delimiter, and passing the
     * following String: <code>"|this|is|a|string||that|has|"</code>.
     * 
     * @param src Source String.
     * @param delim Delimiter separating fields.
     * @return Number of fields in source String.
     */
    public static int getNumFieldsFromString (String src, String delim)
    {
        StringTokenizer m_tokenizer = new StringTokenizer (src, delim);
        return m_tokenizer.countTokens ();
    }

    /**
     * Gets the number of fields in the passed in String. Two delimiters next to
     * each other and a starting or ending delimiter are considered fields. For
     * example executing this method on the following String
     * "|this|is|a|string||that|has|", and using "|" as the delimiter, would
     * return a result of 9.
     * 
     * @param str The string to count fields on.
     * @param delim The field delimiter
     * @return The number of fields. If the str is null or has no length, 0 is
     *         returned. If the delim is null, then 1 is returned.
     */
    public static int getAbsoluteNumFieldsFromString (String str, String delim)
    {
        if (str == null || str.length () == 0)
        {
            return (0);
        }

        if (delim == null)
        {
            return (1);
        }

        int delimSize = delim.length ();

        int count = 1;
        int offset = -delimSize;
        for (; (offset = str.indexOf (delim, offset + delimSize)) != -1; count++)
        {
        }
        return (count);
    }

    /**
     * Splits up the inputStr and returns an arraylist of Strings.
     * 
     * @param inputStr Source String.
     * @param delim Delimiter separating fields.
     * @return An array of String.
     */
    public static ArrayList <String> getArrayList (String inputStr, String delim)
    {
        ArrayList <String> list = new ArrayList <String> ();
        StringTokenizer tokens = new StringTokenizer (inputStr.trim (), delim);
        while (tokens.hasMoreElements ())
        {
            list.add (tokens.nextToken ().trim ());
        }
        return list;
    }

    /**
     * Splits a String into a String array. The string is split into segments
     * based on the passed in maxWidth. The last element of the array may
     * contain less than maxWidth characters, all others will contain maxWidth
     * number of characters.
     * 
     * @param inputStr The String to split
     * @param maxWidth The maximum width for each array element.
     * @return A String array. If inputStr is null, an empty array is returned.
     * @throws IllegalArgumentException if maxWidth is not positive.
     */
    public static String[] getArray (String inputStr, int maxWidth) throws IllegalArgumentException
    {
        if (inputStr == null)
        {
            return (new String[0]);
        }

        if (maxWidth <= 0)
        {
            throw new IllegalArgumentException ("maxWidth must be positive");
        }

        int length = inputStr.length ();
        int size = length / maxWidth;
        int rem = length % maxWidth;

        if (length == 0)
        {
            size = 1;
        }

        if (rem > 0)
        {
            size++;
        }

        String[] result = new String[size];
        int lastIndex = 0;
        int end = 0;

        for (int i = 0; i < length; i++)
        {
            end = lastIndex + maxWidth;
            if (end < length)
            {
                result[i] = inputStr.substring (lastIndex, end);
            }
            else
            {
                result[i] = inputStr.substring (lastIndex);
                break;
            }
            lastIndex = end;
        }

        return (result);

    }

    /**
     * Splits up the inputStr and returns an array of Strings.
     * 
     * @param inputStr Source String.
     * @param delim Delimiter separating fields.
     * @return An array of String, whose elements have been trimmed.
     */
    public static String[] getArray (String inputStr, String delim)
    {
        Vector <String> v = new Vector <String> ();
        StringTokenizer tokens = new StringTokenizer (inputStr.trim (), delim);
        while (tokens.hasMoreElements ())
        {
            v.addElement (tokens.nextToken ().trim ());
        }

        String[] array = new String[v.size ()];
        v.copyInto (array);
        return array;
    }

    /**
     * Splits up the inputStr and returns an array of Strings.
     * 
     * @param inputStr Source String.
     * @param delim Delimiter separating fields.
     * @return An array of String.
     */
    public static String[] getArrayNoTrim (String inputStr, String delim)
    {
        Vector <String> v = new Vector <String> ();
        StringTokenizer tokens = new StringTokenizer (inputStr, delim);
        while (tokens.hasMoreElements ())
        {
            v.addElement (tokens.nextToken ());
        }

        String[] array = new String[v.size ()];
        v.copyInto (array);
        return array;
    }

    /**
     * Splits a String by the passed in delimiter. If two delimiters are next to
     * each other, a null element is added to the array. If the passed in str is
     * null, a zero length array is returned.
     * 
     * @param str The String to convert into an array.
     * @param delim The delimiter to use.
     * @return The String array.
     */
    public static String[] getPositionalArrayWithNulls (String str, char delim)
    {
        if (str == null)
        {
            return (new String[0]);
        }

        ArrayList <String> a = new ArrayList <String> (15);
        int offset = 0;
        int index = 0;
        while ((index = str.indexOf (delim, offset)) != -1)
        {
            if (index == offset)
            {
                a.add (null);
            }
            else
            {
                a.add (str.substring (offset, index));
            }
            offset = index + 1;
        }

        // Add any data at the end
        if (offset == str.length ())
        {
            a.add (null);
        }
        else
        {
            a.add (str.substring (offset));
        }

        String[] result = new String[a.size ()];
        a.toArray (result);
        return (result);
    }

    /**
     * Splits up the inStr and returns an array of Strings. If the delim char
     * appears twice an empty String is inserted into the array. If inStr is
     * null, a 1 element String array is returned, whose value is null.
     * 
     * @param str Source String.
     * @param delim Delimiter separating fields.
     * @return An array of String.
     */
    public static String[] getPositionalArray (String str, char delim)
    {
        if (str == null)
        {
            return (new String[]
            { null });
        }

        ArrayList <String> a = new ArrayList <String> (15);
        int offset = 0;
        int index = 0;
        while ((index = str.indexOf (delim, offset)) != -1)
        {
            if (index == offset)
            {
                a.add ("");
            }
            else
            {
                a.add (str.substring (offset, index));
            }
            offset = index + 1;
        }

        // Add any data at the end
        if (offset == str.length ())
        {
            a.add ("");
        }
        else
        {
            a.add (str.substring (offset));
        }

        String[] result = new String[a.size ()];
        a.toArray (result);
        return (result);
    }

    /**
     * This method deletes the specified character from the string and returns
     * the new string.
     * 
     * @param inStr Source String.
     * @param delChar Character to delete.
     * @return newString
     */
    public static String delChar (String inStr, char delChar)
    {
        StringBuffer buff = new StringBuffer ();
        for (int x = 0; x < inStr.length (); x++)
        {
            if (inStr.charAt (x) != delChar)
            {
                buff.append (inStr.charAt (x));
            }
        }
        return buff.toString ();
    }

    /**
     * This method sorts the arrayList which consists of Strings.
     * 
     * @param orig
     * @return an ArrayList sorted
     */
    public static ArrayList <String> sortArrayList (ArrayList <String> orig)
    {
        ArrayList <String> sorted = new ArrayList <String> (orig);
        Collections.sort (sorted);
        return sorted;
    }

    /**
     * This method deletes any duplicates in the arrayList which consists of
     * Strings.
     * 
     * @param orig
     * @return sorted
     */
    public static ArrayList <String> makeUniqueArrayList (ArrayList <String> orig)
    {
        // Make the output array list large enough so it won't have to grow
        ArrayList <String> uList = new ArrayList <String> (orig.size ());
        // A HashMap to keep track of the items already on the list
        HashMap <String, String> existing = new HashMap <String, String> ();

        // For each entry in the original array, look for it in the HashMap
        // If it is already there, its a duplicate, skip it;
        // If it isn't already there, its not a duplicate, add it to the
        // output list and the HashMap
        for (int x = 0; x < orig.size (); x++)
        {
            String item = (String) orig.get (x);
            if (existing.get (item) == null)
            {
                uList.add (item);
                existing.put (item, item);
            }
        }

        return uList;
    }

    /**
     * Replace every occurence of 'pattern' with 'replace' in string 'str'.
     * 
     * @param str the input string
     * @param pattern the pattern to be replaced
     * @param replace the text to replace pattern with
     * @return String the substituted string
     */
    public static String replace (String str, String pattern, String replace)
    {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer ();

        while ((e = str.indexOf (pattern, s)) >= 0)
        {
            result.append (str.substring (s, e));
            result.append (replace);
            s = e + pattern.length ();
        }

        result.append (str.substring (s));
        return result.toString ();
    }

    /**
     * Aligns the text on the right. If the input string is longer than column
     * width, it is truncated. If the input string is shorter than column width,
     * it is padded on the left with the specified character.
     * 
     * @param str the input string
     * @param colWidth the desired string length
     * @param c character to pad with
     * @return String padded or truncated input string
     */
    public static String rightAlign (String str, int colWidth, char c)
    {
        String fStr = str;
        int len = colWidth - str.length ();
        if (len > 0)
        {
            fStr = lFill (str, len, c);
        }
        return (fStr.substring (0, colWidth));
    }

    /**
     * Centers text within the specified width. If the input string is longer
     * than column width, it is truncated. If the input string is shorter than
     * column width, it is padded on the left and right with the specified
     * character.
     * 
     * @param str the input string
     * @param colWidth the desired string length
     * @param c character to pad with
     * @return String padded or truncated input string
     */
    public static String centerAlign (String str, int colWidth, char c)
    {
        int len = ((colWidth - str.length ()) / 2);
        String fStr = pad (lFill (str, len, c), colWidth, c);
        return (fStr.substring (0, colWidth));
    }

    /**
     * Aligns the text to the left. If the input string is longer than column
     * width, it is truncated. If the input string is shorter than column width,
     * it is padded on the right with the specified character.
     * 
     * @param str the input string
     * @param colWidth the desired string length
     * @param c character to pad with
     * @return String padded or truncated input string
     */
    public static String LeftAlign (String str, int colWidth, char c)
    {
        return (pad (str, colWidth, c));
    }

    /**
     * Fill in the number of specified characters to the left of the specified
     * string.
     * 
     * @param str the input string
     * @param num the number of characters to insert at the beginning of the
     *            string
     * @param c the character to insert
     * @return the padded string
     */
    public static String lFill (String str, int num, char c)
    {
        StringBuffer buff = new StringBuffer (str);
        for (int x = 0; x < num; x++)
        {
            buff.insert (0, c);
        }
        return (buff.toString ());
    }

    /**
     * Ensure that the string 'str' is exactly 'len' characters long, by padding
     * it on the right with the character 'c' if it is too short, or truncating
     * it if it is too long.
     * 
     * @param str the input string
     * @param len the desired length
     * @param c the string to pad with
     * @return String the padded or truncated string
     */
    public static String pad (String str, int len, char c)
    {
        String rval = str + makeString (len, c);
        return rval.substring (0, len);
    }

    /**
     * Make a string of consisting of the char 'c' repeated 'len' times.
     * 
     * @param len the desired length
     * @param c the character to use
     * @return String a string consisting of <code>c</code> repeated
     *         <code>len</code> times.
     */
    public static String makeString (int len, char c)
    {
        StringBuffer sb = new StringBuffer ();
        for (int i = 0; i < len; i++)
        {
            sb.append (c);
        }
        return sb.toString ();
    }

    /**
     * Construct a string by concatenating all of the elements in a List
     * together, placing a delimiter between each element. The 'toString()'
     * function is assumed to be defined meaningfully for each List element.
     * 
     * @param lst list containing the items to concatenate
     * @param delim string to place between items
     * @return a String resulting string
     */
    public static String join (List <String> lst, String delim)
    {
        if (lst.size () == 0)
            return ""; // special case

        ListIterator <String> it = lst.listIterator ();

        // Initialize output with first item
        StringBuffer rval = new StringBuffer (it.next ().toString ());

        // Add the rest
        while (it.hasNext ())
        {
            rval.append (delim);
            rval.append (it.next ().toString ());
        }

        return rval.toString ();
    }

    /**
     * Check if all characters of a string are alphanumeric.
     * 
     * The a character is considered to be alphanumeric if
     * Character.isLetterOrDigit() returns true.
     * 
     * @param s The string to check
     * @return True if all characters in the string are alphanumeric.
     */
    public static boolean isAlphanumeric (String s)
    {
        if (s == null)
            return false;

        for (int i = 0; i < s.length (); i++)
        {
            if (!Character.isLetterOrDigit (s.charAt (i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if all characters of a string are numeric.
     * 
     * The a character is considered to be numeric if Character.isDigit()
     * returns true.
     * 
     * @param s The string to check
     * @return True if all characters in the string are digits.
     */
    public static boolean isNumeric (String s)
    {
        if (s == null)
            return false;

        for (int i = 0; i < s.length (); i++)
        {
            if (!Character.isDigit (s.charAt (i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Replace all variables in a string with their associated values.
     * 
     * Variables are enclosed in %, like %jspPath% The variable values are taken
     * from the input properties object. Variables without associated values in
     * the properties object will be left unexpanded.
     * 
     * @param input The string with zero to n variables
     * @param p The properties hash to find associated values.
     * @return the substitued string
     * 
     */
    public static String expandVar (String input, Properties p)
    {
        // Process the input line and substitute the environment variables
        StringBuffer output = new StringBuffer ();
        int pctChar;
        int curChar = 0;

        while ((pctChar = input.indexOf ('%', curChar)) != -1)
        {
            // Copy from current location up to, but not including, the %
            if (pctChar > curChar)
            {
                output.append (input.substring (curChar, pctChar));
            }

            // Get the name of the environment variable
            curChar = pctChar + 1;
            pctChar = input.indexOf ('%', curChar);
            if (pctChar == -1)
            {
                // No trailing %, just include the % and the rest of the line
                output.append (input.substring (curChar - 1, input.length ()));
                curChar = input.length ();
                break;
            }
            String envName = input.substring (curChar, pctChar);
            String envValue = p.getProperty (envName);
            if (envValue != null)
            {
                // Append the value of the environment variable
                output.append (envValue);
            }
            else
            {
                // Can't find value for variable. Keep original pattern.
                output.append (input.substring (curChar - 1, pctChar + 1));
            }

            // Keep going
            curChar = pctChar + 1;
        }
        // Get the stuff after the last variable
        if (curChar < input.length ())
        {
            output.append (input.substring (curChar, input.length ()));
        }

        return output.toString ();
    }

    /**
     * Returns the env variables as properties.
     * 
     * @return properties
     */
    public static Properties getEnvVar ()
    {
        Properties env = new Properties ();
        Map <String, String> envs = System.getenv ();
        for (Map.Entry <String, String> entry : envs.entrySet ())
        {
            env.put (entry.getKey (), entry.getValue ());
        }
        return env;
    }

    /**
     * Returns the string with any env variables expanded %PINFLY_HOME%.
     * 
     * @param input
     * @return string with expanded variables
     */
    public static String expandEnvVar (String input)
    {
        return StringUtil.expandVar (input, getEnvVar ());
    }

    /**
     * Replace the XML reserved characters ( <, >, &, ', and ") with their
     * respective entity references.
     * 
     * @param str The string to operate on
     * @return The escaped string. Return null if the passed in string is null.
     */
    public static String escapeXML (String str)
    {
        if (str == null)
        {
            return (null);
        }

        // Most strings don't have any characters to replace. In that
        // case, copying the string character by character is quite
        // expensive. Its worth while to make sure there is something to
        // do before actually starting
        if (str.indexOf ('"') == -1 && str.indexOf ('\'') == -1 && str.indexOf ('&') == -1 && str.indexOf ('<') == -1
            && str.indexOf ('>') == -1)
        {
            return str;
        }

        char[] chars = str.toCharArray ();
        int length = chars.length;
        StringBuffer out = new StringBuffer (length * 2);

        for (int i = 0; i < length; i++)
        {
            char c = chars[i];
            switch (c)
            {
            case '<':
                out.append ("&lt;");
                break;

            case '>':
                out.append ("&gt;");
                break;

            case '&':
                out.append ("&amp;");
                break;

            case '"':
                out.append ("&quot;");
                break;

            case '\'':
                out.append ("&apos;");
                break;

            default:
                out.append (c);
                break;
            }
        }

        return out.toString ();
    }

    /**
     * Decodes an XML encoded string which has had its ampersands, less than,
     * greater than, quotes, and apostrophe symbols protected.
     * 
     * @param xml The xml to decode
     * @return The decoded string
     */
    public static String decodeXML (String xml)
    {
        if (xml == null)
        {
            return (null);
        }

        if (xml.length () == 0)
        {
            return (xml);
        }

        // Some strings don't need to be decoded
        if (xml.indexOf ('&') == -1 || xml.indexOf (';') == -1)
        {
            return (xml);
        }

        StringBuffer buff = new StringBuffer ();
        char[] chars = xml.toCharArray ();

        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] == '&')
            {
                // See if this could be a &lt; or &gt;
                if (i + 3 < chars.length)
                {
                    if (chars[i + 1] == 'l' && chars[i + 2] == 't' && chars[i + 3] == ';')
                    {
                        buff.append ('<');
                        i += 3;
                        continue;
                    }
                    else if (chars[i + 1] == 'g' && chars[i + 2] == 't' && chars[i + 3] == ';')
                    {
                        buff.append ('>');
                        i += 3;
                        continue;
                    }
                }

                // See if it could be a &amp;
                if (i + 4 < chars.length)
                {
                    if (chars[i + 1] == 'a' && chars[i + 2] == 'm' && chars[i + 3] == 'p' && chars[i + 4] == ';')
                    {
                        buff.append ('&');
                        i += 4;
                        continue;
                    }
                }

                // See if it could be a &quot; or &apos;
                if (i + 5 < chars.length)
                {
                    if (chars[i + 1] == 'q' && chars[i + 2] == 'u' && chars[i + 3] == 'o' && chars[i + 4] == 't'
                        && chars[i + 5] == ';')
                    {
                        buff.append ('\"');
                        i += 5;
                        continue;
                    }
                    else if (chars[i + 1] == 'a' && chars[i + 2] == 'p' && chars[i + 3] == 'o' && chars[i + 4] == 's'
                             && chars[i + 5] == ';')
                        ;
                    {
                        buff.append ('\'');
                        i += 5;
                        continue;
                    }
                }
            }
            buff.append (chars[i]);
        }

        return (buff.toString ());
    }

    /**
     * Compare two strings for equality. This method differs from
     * <code>String.equals()</code> because it handles <code>null</code>
     * strings. Two <code>null</code> strings are considered to be equal to each
     * other; a <code>null</code> string is not equal to any
     * <code>non-null</code> string.
     * 
     * @param s1 One of the strings to compare
     * @param s2 The other string to compare
     * @return <code>true</code> if s1 and s2 are both <code>null</code> or are
     *         equal to each other.
     */
    public static boolean sameString (String s1, String s2)
    {
        if (s1 != null)
        {
            return s1.equals (s2);
        }
        else
        {
            return s2 == null;
        }
    }

    /**
     * Convert value to boolean
     * 
     * Converts an attribute value to a boolean. If the value is 'yes' or 'true'
     * (ignoring case) then returns true; if the value is 'no' or 'false'
     * (ignoring case) returns false. If value is anything else, throws an
     * IllegalArgumentException.
     * 
     * @param value The string value to be converted
     * @return boolean representation of value
     * @throws IllegalArgumentException If value can't be converted to boolean
     */
    public static boolean getBooleanValue (String value) throws IllegalArgumentException
    {
        if ("true".equalsIgnoreCase (value) || "yes".equalsIgnoreCase (value))
        {
            return true;
        }

        if ("false".equalsIgnoreCase (value) || "no".equalsIgnoreCase (value))
        {
            return false;
        }

        throw new IllegalArgumentException (value + " can't be converted to boolean");
    }

    /**
     * Truncates the passed in String to a maximum width.
     * 
     * @param s The String to truncate
     * @param maxWidth The maximum number of characters that the String will
     *            have after truncation.
     * @return The truncated String.
     */
    public static String truncate (String s, int maxWidth)
    {
        return (truncate (s, maxWidth, ""));
    }

    /**
     * Truncates the passed in String to a maximum width.
     * 
     * @param s The String to truncate
     * @param maxWidth The maximum number of characters that the String will
     *            have after truncation.
     * @param truncateIndicator A String to append to the end of result string,
     *            only if truncation actually occured. This allows one to
     *            clearly indicate that the string was truncated. These extra
     *            characters are not included in the truncation size calculation
     *            for the String. This means that if truncateIndicator is
     *            provided, the result String will be larger than maxWidth by
     *            the size of the truncateIndicator.
     * @return The truncated String.
     */
    public static String truncate (String s, int maxWidth, String truncateIndicator)
    {
        if (s == null)
        {
            return (null);
        }

        if (s.length () <= maxWidth)
        {
            return (s);
        }

        return (s.substring (0, maxWidth) + truncateIndicator);
    }

    /**
     * Folds the passed in string using the default width of
     * {@link #DEFAULT_FOLD_WIDTH}. The method attempts to preserve words across
     * lines if it can find a whitespace character to the left of the desired
     * maxWidth, and within the {@link #DEFAULT_FOLD_WINDOW}number of
     * characters. If no whitespace character is found within the window, then
     * the line is folded at exactly the desired width, and the word is split in
     * two. The result lines are separated by the default system line separator.
     * 
     * @param text The String that we wish to fold.
     * @return The folded String, or null if text is null.
     */
    public static String fold (String text)
    {
        return (fold (text, DEFAULT_FOLD_WIDTH, LS, DEFAULT_FOLD_WINDOW));
    }

    /**
     * Folds the passed in string at a specified width. The method attempts to
     * preserve words across lines if it can find a whitespace character to the
     * left of the desired maxWidth, and within the {@link #DEFAULT_FOLD_WINDOW}
     * number of characters. If no whitespace character is found within the
     * window, then the line is folded at exactly the desired width, and the
     * word is split in two. The result lines are separated by the default
     * system line separator.
     * 
     * @param text The String that we wish to fold.
     * @param maxWidth The number of characters that we desire the line to be
     *            split at.
     * @return The folded String, or null if text is null.
     * @throws IllegalArgumentException if width is not positive.
     */
    public static String fold (String text, int maxWidth)
    {
        return (fold (text, maxWidth, LS, DEFAULT_FOLD_WINDOW));
    }

    /**
     * Folds the passed in string at a specified width. The method attempts to
     * preserve words across lines if it can find a whitespace character to the
     * left of the desired maxWidth, and within the {@link #DEFAULT_FOLD_WINDOW}
     * number of characters. If no whitespace character is found within the
     * window, then the line is folded at exactly the desired width, and the
     * word is split in two.
     * 
     * @param text The String that we wish to fold.
     * @param maxWidth The number of characters that we desire the line to be
     *            split at.
     * @param separator The line separator to use in the resulting string
     * @return The folded String, or null if text is null.
     * @throws IllegalArgumentException if separator is null or width is not
     *             positive.
     */
    public static String fold (String text, int maxWidth, String separator)
    {
        return (fold (text, maxWidth, separator, DEFAULT_FOLD_WINDOW));
    }

    /**
     * Folds the passed in string at a specified width. The method attempts to
     * preserve words across lines if it can find a whitespace character to the
     * left of the desired maxWidth, and within the window argument number of
     * characters. If no whitespace character is found within the window, then
     * the line is folded at exactly the desired width, and the word is split in
     * two.
     * 
     * @param text The String that we wish to fold.
     * @param maxWidth The number of characters that we desire the line to be
     *            split at.
     * @param separator The line separator to use in the resulting string
     * @param window The number of characters to search left of the width, to
     *            find a white space character to fold at.
     * @return The folded String, or null if text is null.
     * @throws IllegalArgumentException if separator is null.
     * @throws IllegalArgumentException if width is not positive
     * @throws IllegalArgumentException if window is negative.
     */
    public static String fold (String text, int maxWidth, String separator, int window)
    {
        if (text == null)
        {
            return (null);
        }

        if (separator == null)
        {
            throw new IllegalArgumentException ("separator can not be null!");
        }

        if (maxWidth < 1)
        {
            throw new IllegalArgumentException ("maxWidth must be positive!");
        }

        if (window < 0)
        {
            throw new IllegalArgumentException ("window can not be negative!");
        }

        // Put all of the lines into a List, so that they can be processed.
        StringBuffer results = new StringBuffer ();
        List <String> lines = new ArrayList <String> ();
        BufferedReader reader = new BufferedReader (new StringReader (text));
        try
        {
            for (String line = null; (line = reader.readLine ()) != null; lines.add (line))
                ;
        }
        catch (IOException ioe)
        {
            // We are reading a string, how can we get an io exception?
        }
        finally
        {
            try
            {
                reader.close ();
            }
            catch (IOException ie)
            {
            }
        }

        // Now process each of the lines, folding ones that are too long
        // Not using an Iterator here, because we could be adding items
        // to the list while we are looping.
        for (int i = 0; i < lines.size (); i++)
        {
            String line = (String) lines.get (i);
            if (results.length () != 0)
            {
                results.append (separator);
            }

            if (line.length () <= maxWidth)
            {
                // don't need to fold
                results.append (line);
            }
            else
            {
                // See if there is whitespace to the left or equal to the width.
                int foldPoint = findClosestWhiteSpace (line, maxWidth, window);
                if (foldPoint == -1)
                {
                    // didn't find whitespace
                    foldPoint = maxWidth;
                    results.append (line.substring (0, foldPoint));
                }
                else
                {
                    // found whitespace
                    results.append (line.substring (0, foldPoint));

                    // Discard the whitespace character that was found, we are
                    // essentially
                    // replacing it with the separator string.
                    foldPoint++;
                }

                if (foldPoint < line.length ())
                {
                    // There is some leftover that might need to be folded

                    // add it before the next line, so that it will be
                    // processed.
                    lines.add (i + 1, ltrim (line.substring (foldPoint)));
                }
            }
        }

        return (results.toString ());
    }

    /**
     * Searches to the left of the startIndex, looking for the index of a
     * whitespace character, that is within maxSearchLength characters.
     * 
     * @param input The String to search for whitespace in.
     * @param startIndex The starting index in the String to search for
     *            whitespace to the left of.
     * @param maxSearchLength The maximum number of characters to the left of
     *            the startIndex to search for a whitespace character.
     * @return the index of the whitespace character, or -1 if one couldn't be
     *         found within maxSearchLength characters.
     */
    private static int findClosestWhiteSpace (String input, int startIndex, int maxSearchLength)
    {
        for (int index = startIndex; index >= 0 && index >= startIndex - maxSearchLength; index--)
        {
            if (Character.isWhitespace (input.charAt (index)))
            {
                return (index);
            }
        }

        // Didn't find any whitespace in the range.
        return (-1);
    }

    /**
     * Trims whitespace from the right end of the string
     * 
     * @param input The string to trim.
     * @return The trimmed string, or null if input is null. If the String only
     *         contains whitespace, then an empty string is returned.
     */
    public static String rtrim (String input)
    {
        if (input == null)
        {
            return (null);
        }

        if (input.length () == 0)
        {
            return (input);
        }

        char[] chars = input.toCharArray ();

        int i = chars.length - 1;
        for (; i >= 0; --i)
        {
            if (!Character.isWhitespace (chars[i]))
            {
                i++;
                break;
            }
        }

        if (i == -1)
        {
            // The whole string contains whitespace
            return ("");

        }

        return (input.substring (0, i));
    }

    /**
     * Trims whitespace from the left end of the string
     * 
     * @param input The string to trim.
     * @return The trimmed string, or null if input is null. If the String only
     *         contains whitespace, then an empty string is returned.
     */
    public static String ltrim (String input)
    {
        if (input == null)
        {
            return (null);
        }

        if (input.length () == 0)
        {
            return (input);
        }

        char[] chars = input.toCharArray ();

        int i = 0;
        for (; i < chars.length; i++)
        {
            if (!Character.isWhitespace (chars[i]))
            {
                break;
            }
        }

        if (i == chars.length)
        {
            // The whole string contains whitespace
            return ("");
        }

        return (input.substring (i));
    }

    /**
     * 
     * read xml file of unicode format,such as
     * <code><comp id="bLogin" text="\u767b\u5f55"/></code>
     * 
     * Converts encoded &#92;uxxxx to unicode chars and changes special saved
     * chars to their original forms
     * 
     * @param theString need to converts unicode string.
     */
    public static String loadConvert (String theString)
    {
        char aChar;
        int len = theString.length ();
        StringBuffer outBuffer = new StringBuffer (len);

        for (int x = 0; x < len;)
        {
            aChar = theString.charAt (x++);
            if (aChar == '\\')
            {
                aChar = theString.charAt (x++);
                if (aChar == 'u')
                {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++)
                    {
                        aChar = theString.charAt (x++);
                        switch (aChar)
                        {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException ("Malformed \\uxxxx encoding.");
                        }
                    }
                    outBuffer.append ((char) value);
                }
                else
                {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append (aChar);
                }
            }
            else
                outBuffer.append (aChar);
        }
        return outBuffer.toString ();
    }

    /**
     * Indicates whether the specified String object is a null reference or
     * contains no non-whitespace characters.
     * 
     * @param value String reference to test.
     * 
     * @return boolean true if the value parameter is a null reference or a
     *         string with no non-whitespace characters; otherwise, false.
     * 
     */
    public static boolean isNullOrBlank (final String value)
    {
        if (value == null)
        {
            return true;
        }

        for (int i = 0; i < value.length (); i++)
        {
            if (Character.isWhitespace (value.charAt (i)))
            {
                continue;
            }
            else
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks the passed string value and if null or contains only whitespace
     * raises an IllegalArgumentException with an error message built up from
     * the passed parameter name and generic text.
     * 
     * @param paramName Name of the parameter to test.
     * 
     * @param value String reference to test.
     * 
     * @throws IllegalArgumentException if the value parameter is a null
     *             reference or a string with no non-whitespace characters.
     * 
     */
    public static void isNullOrBlankValidated (final String paramName, final String value)
    {
        if (isNullOrBlank (value))
        {
            throw new IllegalArgumentException (paramName + " cannot be null or contain only whitespace");
        }
    }

    /**
     * Checks the passed string value and if null raises an
     * IllegalArgumentException with an error message built up from the passed
     * parameter name and generic text.
     * 
     * @param paramName Name of the parameter to test. Guaranteed to not be
     *            modified.
     * 
     * @param value String reference to test. Guaranteed not to be modified.
     * 
     * @throws IllegalArgumentException if the value parameter is a null
     *             reference.
     * 
     */
    public static void isNullValidated (final String paramName, final String value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException (paramName + " cannot be null");
        }
    }

    /**
     * Is the string non-empty. A non-empty string is one that is not null and
     * not all white space.
     * 
     * @param s The string to test
     * @return <code>true</code> if the string has at least one non-whitespace
     *         character.
     * @see #isEmptyString(String)
     */
    public static boolean isNonEmpty (String s)
    {
        return s != null && s.trim ().length () > 0;
    }

    /**
     * Is the string empty. An empty string is one that is null, has zero
     * length, or contains only whitespace characters.
     * 
     * @param s The string to test
     * @return <code>true</code> if the string is null, empty, or all
     *         whitespace.
     * 
     * @see #isNonEmptyString(String)
     */
    public static boolean isEmpty (String s)
    {
        return !isNonEmpty (s);
    }

    /**
     * Takes an array of objects and returns a formatted string showing its
     * contents.
     * 
     * @param values The array of objects to parse
     * 
     * @param delimiter The character to use between elements in the returned
     *            String
     * 
     * @return String in the format [element1<delim>element2<delim>...elementn]
     */
    public static String arrayToString (Object[] values, char delimiter)
    {
        if (values == null)
        {
            return "[NULL]";
        }

        StringBuffer retVal = new StringBuffer (values.length * 50);
        retVal.append ('[');
        for (int i = 0; i < values.length; i++)
        {
            Object obj = values[i];
            if (i == 0)
            {
                retVal.append (obj.toString ());
            }
            else
            {
                retVal.append (delimiter).append (obj.toString ());
            }
        }

        retVal.append (']');
        return retVal.toString ();
    }

    /**
     * <p>
     * Strips whitespace from the start and end of a String returning
     * <code>null</code> if the String is empty ("") after the strip.
     * </p>
     * 
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     * 
     * <pre>
     * StringUtils.stripToNull(null)     = null
     * StringUtils.stripToNull("")       = null
     * StringUtils.stripToNull("   ")    = null
     * StringUtils.stripToNull("abc")    = "abc"
     * StringUtils.stripToNull("  abc")  = "abc"
     * StringUtils.stripToNull("abc  ")  = "abc"
     * StringUtils.stripToNull(" abc ")  = "abc"
     * StringUtils.stripToNull(" ab c ") = "ab c"
     * </pre>
     * 
     * @param str the String to be stripped, may be null
     * @return the stripped String, <code>null</code> if whitespace, empty or
     *         null String input
     */
    public static String stripToNull (String str)
    {
        if (str == null)
            return null;
        String s = str.trim ();
        return s.length () == 0 ? null : s;
    }

    /**
     * <p>
     * Searches a String for substrings delimited by a start and end tag,
     * returning all matching substrings in an array.
     * </p>
     * 
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A
     * <code>null</code> open/close returns <code>null</code> (no match). An
     * empty ("") open/close returns <code>null</code> (no match).
     * </p>
     * 
     * <pre>
     * StringUtils.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]
     * StringUtils.substringsBetween(null, *, *)            = null
     * StringUtils.substringsBetween(*, null, *)            = null
     * StringUtils.substringsBetween(*, *, null)            = null
     * StringUtils.substringsBetween("", "[", "]")          = []
     * </pre>
     * 
     * @param str the String containing the substrings, null returns null, empty
     *            returns empty
     * @param open the String identifying the start of the substring, empty
     *            returns null
     * @param close the String identifying the end of the substring, empty
     *            returns null
     * @return a String Array of substrings, or <code>null</code> if no match
     */
    @SuppressWarnings ("all")
    public static String[] substringsBetween (String str, String open, String close)
    {
        if (str == null || isEmpty (open) || isEmpty (close))
        {
            return null;
        }
        int strLen = str.length ();
        if (strLen == 0)
        {
            return new String[0];
        }
        int closeLen = close.length ();
        int openLen = open.length ();
        List list = new ArrayList ();
        int pos = 0;
        while (pos < (strLen - closeLen))
        {
            int start = str.indexOf (open, pos);
            if (start < 0)
            {
                break;
            }
            start += openLen;
            int end = str.indexOf (close, start);
            if (end < 0)
            {
                break;
            }
            list.add (str.substring (start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty ())
        {
            return null;
        }
        return (String[]) list.toArray (new String[list.size ()]);
    }

    /**
     * <p>
     * Replaces a String with another String inside a larger String, for the
     * first <code>max</code> values of the search String.
     * </p>
     * 
     * <p>
     * A <code>null</code> reference passed to this method is a no-op.
     * </p>
     * 
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace("", *, *, *)           = ""
     * StringUtils.replace("any", null, *, *)     = "any"
     * StringUtils.replace("any", *, null, *)     = "any"
     * StringUtils.replace("any", "", *, *)       = "any"
     * StringUtils.replace("any", *, *, 0)        = "any"
     * StringUtils.replace("abaa", "a", null, -1) = "abaa"
     * StringUtils.replace("abaa", "a", "", -1)   = "b"
     * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     * 
     * @param text text to search and replace in, may be null
     * @param searchString the String to search for, may be null
     * @param replacement the String to replace it with, may be null
     * @param max maximum number of values to replace, or <code>-1</code> if no
     *            maximum
     * @return the text with any replacements processed, <code>null</code> if
     *         null String input
     */
    public static String replace (String text, String searchString, String replacement, int max)
    {
        if (isEmpty (text) || isEmpty (searchString) || replacement == null || max == 0)
        {
            return text;
        }
        int start = 0;
        int end = text.indexOf (searchString, start);
        if (end == INDEX_NOT_FOUND)
        {
            return text;
        }
        int replLength = searchString.length ();
        int increase = replacement.length () - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        //StrBuilder buf = new StrBuilder (text.length () + increase);
        StringBuilder buf = new StringBuilder (text.length () + increase);
        while (end != INDEX_NOT_FOUND)
        {
            buf.append (text.substring (start, end)).append (replacement);
            start = end + replLength;
            if (--max == 0)
            {
                break;
            }
            end = text.indexOf (searchString, start);
        }
        buf.append (text.substring (start));
        return buf.toString ();
    }

}
