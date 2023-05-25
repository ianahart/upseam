export const registerFormState = {
  firstName: { name: 'firstName', value: '', error: '', type: 'text' },
  lastName: { name: 'lastName', value: '', error: '', type: 'text' },
  email: { name: 'email', value: '', error: '', type: 'email' },
  password: { name: 'password', value: '', error: '', type: 'password' },
  confirmPassword: { name: 'confirmPassword', value: '', error: '', type: 'password' },
};

export const resetPasswordState = {
  password: { name: 'password', value: '', error: '', type: 'password' },
  confirmPassword: { name: 'confirmPassword', value: '', error: '', type: 'password' },
};

export const forgotPasswordFormState = {
  email: { name: 'email', value: '', error: '', type: 'email' },
};

export const userFormState = {
  firstName: { name: 'firstName', value: '', error: '', type: 'text' },
  lastName: { name: 'lastName', value: '', error: '', type: 'text' },
};

export const editProfileState = {
  zipCode: { name: 'zipCode', value: '', error: '', type: 'text' },
  country: { name: 'country', value: '', error: '', type: 'text' },
  address: { name: 'address', value: '', error: '', type: 'text' },
  pricing: { name: 'pricing', value: '', error: '', type: 'text' },
  site: { name: 'site', value: '', error: '', type: 'text' },
  bio: { name: 'bio', value: '', error: '', type: 'text' },
  state: { name: 'state', value: '', error: '', type: 'text' },
};

export const loginFormState = {
  email: { name: 'email', value: '', error: '', type: 'email' },
  password: { name: 'password', value: '', error: '', type: 'password' },
};

export const tokenState = {
  refreshToken: '',
  token: '',
};

export const userState = {
  id: 0,
  firstName: '',
  lastName: '',
  email: '',
  role: '',
  abbreviation: '',
  isLoggedIn: false,
  profileId: 0,
  avatarUrl: '',
};

export const profileState = {
  id: 0,
  avatarUrl: '',
  avatarFileName: '',
  zipCode: '',
  country: '',
  address: '',
  specialities: [],
  pricing: '',
  site: '',
  bio: '',
};

export const countries = [
  { name: 'Afghanistan', id: 1 },
  { name: 'Åland Islands', id: 2 },
  { name: 'Albania', id: 3 },
  { name: 'Algeria', id: 4 },
  { name: 'American Samoa', id: 5 },
  { name: 'AndorrA', id: 6 },
  { name: 'Angola', id: 7 },
  { name: 'Anguilla', id: 8 },
  { name: 'Antarctica', id: 9 },
  { name: 'Antigua and Barbuda', id: 10 },
  { name: 'Argentina', id: 11 },
  { name: 'Armenia', id: 12 },
  { name: 'Aruba', id: 13 },
  { name: 'Australia', id: 14 },
  { name: 'Austria', id: 15 },
  { name: 'Azerbaijan', id: 16 },
  { name: 'Bahamas', id: 17 },
  { name: 'Bahrain', id: 18 },
  { name: 'Bangladesh', id: 19 },
  { name: 'Barbados', id: 20 },
  { name: 'Belarus', id: 21 },
  { name: 'Belgium', id: 22 },
  { name: 'Belize', id: 23 },
  { name: 'Benin', id: 24 },
  { name: 'Bermuda', id: 25 },
  { name: 'Bhutan', id: 26 },
  { name: 'Bolivia', id: 27 },
  { name: 'Bosnia and Herzegovina', id: 28 },
  { name: 'Botswana', id: 29 },
  { name: 'Bouvet Island', id: 30 },
  { name: 'Brazil', id: 31 },
  { name: 'British Indian Ocean Territory', id: 32 },
  { name: 'Brunei Darussalam', id: 33 },
  { name: 'Bulgaria', id: 34 },
  { name: 'Burkina Faso', id: 35 },
  { name: 'Burundi', id: 36 },
  { name: 'Cambodia', id: 37 },
  { name: 'Cameroon', id: 38 },
  { name: 'Canada', id: 39 },
  { name: 'Cape Verde', id: 40 },
  { name: 'Cayman Islands', id: 41 },
  { name: 'Central African Republic', id: 42 },
  { name: 'Chad', id: 43 },
  { name: 'Chile', id: 44 },
  { name: 'China', id: 45 },
  { name: 'Christmas Island', id: 46 },
  { name: 'Cocos (Keeling) Islands', id: 47 },
  { name: 'Colombia', id: 48 },
  { name: 'Comoros', id: 49 },
  { name: 'Congo', id: 50 },
  { name: 'Congo, The Democratic Republic of the', id: 51 },
  { name: 'Cook Islands', id: 52 },
  { name: 'Costa Rica', id: 53 },
  { name: "Cote D'Ivoire", id: 54 },
  { name: 'Croatia', id: 55 },
  { name: 'Cuba', id: 56 },
  { name: 'Cyprus', id: 57 },
  { name: 'Czech Republic', id: 58 },
  { name: 'Denmark', id: 59 },
  { name: 'Djibouti', id: 60 },
  { name: 'Dominica', id: 61 },
  { name: 'Dominican Republic', id: 62 },
  { name: 'Ecuador', id: 63 },
  { name: 'Egypt', id: 64 },
  { name: 'El Salvador', id: 65 },
  { name: 'Equatorial Guinea', id: 66 },
  { name: 'Eritrea', id: 67 },
  { name: 'Estonia', id: 68 },
  { name: 'Ethiopia', id: 69 },
  { name: 'Falkland Islands (Malvinas)', id: 70 },
  { name: 'Faroe Islands', id: 71 },
  { name: 'Fiji', id: 72 },
  { name: 'Finland', id: 73 },
  { name: 'France', id: 74 },
  { name: 'French Guiana', id: 75 },
  { name: 'French Polynesia', id: 76 },
  { name: 'French Southern Territories', id: 77 },
  { name: 'Gabon', id: 78 },
  { name: 'Gambia', id: 79 },
  { name: 'Georgia', id: 80 },
  { name: 'Germany', id: 81 },
  { name: 'Ghana', id: 82 },
  { name: 'Gibraltar', id: 83 },
  { name: 'Greece', id: 84 },
  { name: 'Greenland', id: 85 },
  { name: 'Grenada', id: 86 },
  { name: 'Guadeloupe', id: 87 },
  { name: 'Guam', id: 88 },
  { name: 'Guatemala', id: 89 },
  { name: 'Guernsey', id: 90 },
  { name: 'Guinea', id: 91 },
  { name: 'Guinea-Bissau', id: 92 },
  { name: 'Guyana', id: 93 },
  { name: 'Haiti', id: 94 },
  { name: 'Heard Island and Mcdonald Islands', id: 95 },
  { name: 'Holy See (Vatican City State)', id: 96 },
  { name: 'Honduras', id: 97 },
  { name: 'Hong Kong', id: 98 },
  { name: 'Hungary', id: 99 },
  { name: 'Iceland', id: 100 },
  { name: 'India', id: 101 },
  { name: 'Indonesia', id: 102 },
  { name: 'Iran, Islamic Republic Of', id: 103 },
  { name: 'Iraq', id: 104 },
  { name: 'Ireland', id: 105 },
  { name: 'Isle of Man', id: 106 },
  { name: 'Israel', id: 107 },
  { name: 'Italy', id: 108 },
  { name: 'Jamaica', id: 109 },
  { name: 'Japan', id: 110 },
  { name: 'Jersey', id: 111 },
  { name: 'Jordan', id: 112 },
  { name: 'Kazakhstan', id: 113 },
  { name: 'Kenya', id: 114 },
  { name: 'Kiribati', id: 115 },
  { name: "Korea, Democratic People'S Republic of", id: 116 },
  { name: 'Korea, Republic of', id: 117 },
  { name: 'Kuwait', id: 118 },
  { name: 'Kyrgyzstan', id: 119 },
  { name: "Lao People'S Democratic Republic", id: 120 },
  { name: 'Latvia', id: 121 },
  { name: 'Lebanon', id: 122 },
  { name: 'Lesotho', id: 123 },
  { name: 'Liberia', id: 124 },
  { name: 'Libyan Arab Jamahiriya', id: 125 },
  { name: 'Liechtenstein', id: 126 },
  { name: 'Lithuania', id: 127 },
  { name: 'Luxembourg', id: 128 },
  { name: 'Macao', id: 129 },
  { name: 'Macedonia, The Former Yugoslav Republic of', id: 130 },
  { name: 'Madagascar', id: 131 },
  { name: 'Malawi', id: 132 },
  { name: 'Malaysia', id: 133 },
  { name: 'Maldives', id: 134 },
  { name: 'Mali', id: 135 },
  { name: 'Malta', id: 136 },
  { name: 'Marshall Islands', id: 137 },
  { name: 'Martinique', id: 138 },
  { name: 'Mauritania', id: 139 },
  { name: 'Mauritius', id: 140 },
  { name: 'Mayotte', id: 141 },
  { name: 'Mexico', id: 142 },
  { name: 'Micronesia, Federated States of', id: 143 },
  { name: 'Moldova, Republic of', id: 144 },
  { name: 'Monaco', id: 145 },
  { name: 'Mongolia', id: 146 },
  { name: 'Montserrat', id: 147 },
  { name: 'Morocco', id: 148 },
  { name: 'Mozambique', id: 149 },
  { name: 'Myanmar', id: 150 },
  { name: 'Namibia', id: 151 },
  { name: 'Nauru', id: 152 },
  { name: 'Nepal', id: 153 },
  { name: 'Netherlands', id: 154 },
  { name: 'Netherlands Antilles', id: 155 },
  { name: 'New Caledonia', id: 156 },
  { name: 'New Zealand', id: 157 },
  { name: 'Nicaragua', id: 158 },
  { name: 'Niger', id: 159 },
  { name: 'Nigeria', id: 160 },
  { name: 'Niue', id: 161 },
  { name: 'Norfolk Island', id: 162 },
  { name: 'Northern Mariana Islands', id: 163 },
  { name: 'Norway', id: 164 },
  { name: 'Oman', id: 165 },
  { name: 'Pakistan', id: 166 },
  { name: 'Palau', id: 167 },
  { name: 'Palestinian Territory, Occupied', id: 168 },
  { name: 'Panama', id: 169 },
  { name: 'Papua New Guinea', id: 170 },
  { name: 'Paraguay', id: 171 },
  { name: 'Peru', id: 172 },
  { name: 'Philippines', id: 173 },
  { name: 'Pitcairn', id: 174 },
  { name: 'Poland', id: 175 },
  { name: 'Portugal', id: 176 },
  { name: 'Puerto Rico', id: 177 },
  { name: 'Qatar', id: 178 },
  { name: 'Reunion', id: 179 },
  { name: 'Romania', id: 180 },
  { name: 'Russian Federation', id: 181 },
  { name: 'RWANDA', id: 182 },
  { name: 'Saint Helena', id: 183 },
  { name: 'Saint Kitts and Nevis', id: 184 },
  { name: 'Saint Lucia', id: 185 },
  { name: 'Saint Pierre and Miquelon', id: 186 },
  { name: 'Saint Vincent and the Grenadines', id: 187 },
  { name: 'Samoa', id: 188 },
  { name: 'San Marino', id: 189 },
  { name: 'Sao Tome and Principe', id: 190 },
  { name: 'Saudi Arabia', id: 191 },
  { name: 'Senegal', id: 192 },
  { name: 'Serbia and Montenegro', id: 193 },
  { name: 'Seychelles', id: 194 },
  { name: 'Sierra Leone', id: 195 },
  { name: 'Singapore', id: 196 },
  { name: 'Slovakia', id: 197 },
  { name: 'Slovenia', id: 198 },
  { name: 'Solomon Islands', id: 199 },
  { name: 'Somalia', id: 200 },
  { name: 'South Africa', id: 201 },
  { name: 'South Georgia and the South Sandwich Islands', id: 202 },
  { name: 'Spain', id: 203 },
  { name: 'Sri Lanka', id: 204 },
  { name: 'Sudan', id: 205 },
  { name: 'Suriname', id: 206 },
  { name: 'Svalbard and Jan Mayen', id: 207 },
  { name: 'Swaziland', id: 208 },
  { name: 'Sweden', id: 209 },
  { name: 'Switzerland', id: 210 },
  { name: 'Syrian Arab Republic', id: 211 },
  { name: 'Taiwan, Province of China', id: 212 },
  { name: 'Tajikistan', id: 213 },
  { name: 'Tanzania, United Republic of', id: 214 },
  { name: 'Thailand', id: 215 },
  { name: 'Timor-Leste', id: 216 },
  { name: 'Togo', id: 217 },
  { name: 'Tokelau', id: 218 },
  { name: 'Tonga', id: 219 },
  { name: 'Trinidad and Tobago', id: 220 },
  { name: 'Tunisia', id: 221 },
  { name: 'Turkey', id: 222 },
  { name: 'Turkmenistan', id: 223 },
  { name: 'Turks and Caicos Islands', id: 224 },
  { name: 'Tuvalu', id: 225 },
  { name: 'Uganda', id: 226 },
  { name: 'Ukraine', id: 227 },
  { name: 'United Arab Emirates', id: 228 },
  { name: 'United Kingdom', id: 229 },
  { name: 'United States', id: 230 },
  { name: 'United States Minor Outlying Islands', id: 231 },
  { name: 'Uruguay', id: 232 },
  { name: 'Uzbekistan', id: 233 },
  { name: 'Vanuatu', id: 234 },
  { name: 'Venezuela', id: 235 },
  { name: 'Viet Nam', id: 236 },
  { name: 'Virgin Islands, British', id: 237 },
  { name: 'Virgin Islands, U.S.', id: 238 },
  { name: 'Wallis and Futuna', id: 239 },
  { name: 'Western Sahara', id: 240 },
  { name: 'Yemen', id: 241 },
  { name: 'Zambia', id: 242 },
  { name: 'Zimbabwe', id: 243 },
];

export const states = [
  {
    name: 'AL',
    id: 1,
  },
  {
    name: 'AK',
    id: 2,
  },
  {
    name: 'AS',
    id: 3,
  },
  {
    name: 'AZ',
    id: 4,
  },
  {
    name: 'AR',
    id: 5,
  },
  {
    name: 'CA',
    id: 6,
  },
  {
    name: 'CO',
    id: 7,
  },
  {
    name: 'CT',
    id: 8,
  },
  {
    name: 'DE',
    id: 9,
  },
  {
    name: 'DC',
    id: 10,
  },
  {
    name: 'FM',
    id: 11,
  },
  {
    name: 'FL',
    id: 12,
  },
  {
    name: 'GA',
    id: 13,
  },
  {
    name: 'GU',
    id: 14,
  },
  {
    name: 'HI',
    id: 15,
  },
  {
    name: 'ID',
    id: 16,
  },
  {
    name: 'IL',
    id: 17,
  },
  {
    name: 'IN',
    id: 18,
  },
  {
    name: 'IA',
    id: 19,
  },
  {
    name: 'KS',
    id: 20,
  },
  {
    name: 'KY',
    id: 21,
  },
  {
    name: 'LA',
    id: 22,
  },
  {
    name: 'ME',
    id: 23,
  },
  {
    name: 'MH',
    id: 24,
  },
  {
    name: 'MD',
    id: 25,
  },
  {
    name: 'MA',
    id: 26,
  },
  {
    name: 'MI',
    id: 27,
  },
  {
    name: 'MN',
    id: 28,
  },
  {
    name: 'MS',
    id: 29,
  },
  {
    name: 'MO',
    id: 30,
  },
  {
    name: 'MT',
    id: 31,
  },
  {
    name: 'NE',
    id: 32,
  },
  {
    name: 'NV',
    id: 33,
  },
  {
    name: 'NH',
    id: 34,
  },
  {
    name: 'NJ',
    id: 35,
  },
  {
    name: 'NM',
    id: 36,
  },
  {
    name: 'NY',
    id: 37,
  },
  {
    name: 'NC',
    id: 38,
  },
  {
    name: 'ND',
    id: 39,
  },
  {
    name: 'OH',
    id: 40,
  },
  {
    name: 'OK',
    id: 41,
  },
  {
    name: 'OR',
    id: 42,
  },
  {
    name: 'PA',
    id: 43,
  },
  {
    name: 'RI',
    id: 44,
  },
  {
    name: 'SC',
    id: 45,
  },
  {
    name: 'SD',
    id: 46,
  },
  {
    name: 'TN',
    id: 47,
  },
  {
    name: 'TX',
    id: 48,
  },
  {
    name: 'UT',
    id: 49,
  },
  {
    name: 'VT',
    id: 50,
  },
  {
    name: 'VA',
    id: 51,
  },
  {
    name: 'WA',
    id: 52,
  },
  {
    name: 'WV',
    id: 53,
  },
  {
    name: 'WI',
    id: 54,
  },
  {
    name: 'WY',
    id: 55,
  },
];

export const pricing = [
  { id: 1, price: '1-25$' },
  { id: 2, price: '25-50$' },
  { id: 3, price: '50-100$' },
  { id: 4, price: '100-200$' },
  { id: 5, price: '200-500$' },
  { id: 6, price: '500-1000$' },
  { id: 7, price: '1000-2000$' },
];
