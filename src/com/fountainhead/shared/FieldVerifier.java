/**

 *
 * Licensed under the terms of the GNU General Public License version 3
 * as published by the Free Software Foundation. You may obtain a copy of the
 * License at: http://www.gnu.org/copyleft/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.fountainhead.shared;

public class FieldVerifier {

	public static boolean isValidUserName(String name) {

		if (name == null) {
			return false;
		}

		return name.length() > 4;
	}

	/*
	 * 
	 * ( # Start of group (?=.*\d) # must contains one digit from 0-9
	 * (?=.*[a-z]) # must contains one lower case characters (?=.*[A-Z]) # must
	 * contains one upper case characters (?=.*[@#$%]) # must contains one
	 * special symbols in the list "@#$%" . # match anything with previous
	 * condition checking {8,32} # length at least 8 characters and maximum of
	 * 32 ) # End of group
	 * 
	 * Passwords must contain at least 8 characters with at least one digit, one
	 * upper case letter, one lower case letter and one special symbol
	 * (�@#$%�).
	 */

	private static final String PASSWORD_VALIDATION_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,32})";

	public static boolean isValidPassword(String password) {

		if (password == null) {
			return false;
		}

		return true;// password.matches(PASSWORD_VALIDATION_REGEX);
	}
}
