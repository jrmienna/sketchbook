from django.db import models

class Gossip(models.Model):
    title = models.CharField(max_length=128)
    date = models.DateTimeField(auto_now_add=True)
    description = models.TextField()
