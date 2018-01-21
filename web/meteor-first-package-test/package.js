Package.describe({
  name: 'mienna:first',
  version: '0.0.1',
  // Brief, one-line summary of the package.
  summary: 'First meteor package',
  // URL to the Git repository containing the source code for this package.
  git: 'https://github.com/mienna/meteor-first-package',
  // By default, Meteor will default to using README.md for documentation.
  // To avoid submitting documentation, set this field to null.
  documentation: 'README.md'
});

Package.onUse(function(api) {
  api.versionsFrom('1.1.0.2');
  api.use('templating', 'client');

  api.addFiles('lib/first.html', 'client');
  api.addFiles('lib/first.js');

});

Package.onTest(function(api) {
  api.use('tinytest');
  api.use('mienna:first');
  api.addFiles('test/first-test.js');
});
