
package com.newinit.filter;

import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author ceasar
 */
public class XSSUtils {

private XSSUtils()
{

}

public static String stripXSS(String value) {
    return value == null ? value : StringEscapeUtils.escapeHtml(value);
  }
}
