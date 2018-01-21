# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('gossip', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='gossip',
            name='date',
            field=models.DateTimeField(auto_now_add=True),
        ),
        migrations.AlterField(
            model_name='gossip',
            name='title',
            field=models.CharField(max_length=128),
        ),
    ]
