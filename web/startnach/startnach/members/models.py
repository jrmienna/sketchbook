from django.db import models

class Member(models.Model):
    pic = models.ImageField(upload_to='members/%Y', blank=True, null=True)
    name = models.CharField(max_length=64)
    points = models.IntegerField(default=0)

