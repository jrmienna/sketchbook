<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://codex.wordpress.org/Editing_wp-config.php
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define('DB_NAME', 'wordpress');

/** MySQL database username */
define('DB_USER', 'root');

/** MySQL database password */
define('DB_PASSWORD', 'root');

/** MySQL hostname */
define('DB_HOST', 'localhost');

/** Database Charset to use in creating database tables. */
define('DB_CHARSET', 'utf8mb4');

/** The Database Collate type. Don't change this if in doubt. */
define('DB_COLLATE', '');

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define('AUTH_KEY',         'kp!_cf+=g7Fg~qR=4|nvPsYAB3~Ok_P/Qxla-9*E=O}/)%I!hO1%b}B,%O}q-q,P');
define('SECURE_AUTH_KEY',  'Dfi#q#t*GooPTnjY G:N|ex+V,9Tzny|>aJ+I=J3VG_owERm8X!8(WRsBD/.v/BI');
define('LOGGED_IN_KEY',    'r|t;apVW[*^;<L`mOq|g1*k=|Uck{$]|gIq|N1,Jh[#AiOLTzc,D.]2~,.HycG&-');
define('NONCE_KEY',        '[j|ka_x,lb3Pn<j}&HBA1Lme-$O};WW:j*1l- p8=FT`qHk$C0gx5/kN /VA|7Q)');
define('AUTH_SALT',        ' 6 yZKgurKDOfG15_!{z4%E]7=.bgDh/*oh,y-U$lqS?4H{urDhK-^gWUBWAY@s)');
define('SECURE_AUTH_SALT', 'Kpes|Ho^ofeW|8cH%ft7.59!](g|r9wuFe7}3Lu=Zf(*y,Wc2zb0Y_t9kIrB6itf');
define('LOGGED_IN_SALT',   '^_a#z_[Z #8xOKCXjbsh9z>quskduh^[Htke)Z#H?jtq?)Ba,[E-uDr9ByeKi{8v');
define('NONCE_SALT',       'AAePrT>zj=Hz5XO-+Rp 01)5FB V,,]*7wGu9DE3se%=(e#qw/..K=6C+@Ic49k3');

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix  = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the Codex.
 *
 * @link https://codex.wordpress.org/Debugging_in_WordPress
 */
define('WP_DEBUG', false);

/* That's all, stop editing! Happy blogging. */

/** Absolute path to the WordPress directory. */
if ( !defined('ABSPATH') )
	define('ABSPATH', dirname(__FILE__) . '/');

/** Sets up WordPress vars and included files. */
require_once(ABSPATH . 'wp-settings.php');

