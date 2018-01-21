import soundcloud

# create client object with app credentials

client = soundcloud.Client(client_id='c4845f147414a8bc98f620b913012879')

#client_secret='e9c9b89d709f3fae0364625451f89982'

tracks = client.get('/tracks', limit=10)
for track in tracks:
    print track.title
app = client.get('/apps/124')
print app.permalink_url

#print client.get('/me').username

