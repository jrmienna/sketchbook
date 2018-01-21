from django.contrib import admin
from startnach.gossip.models import Gossip

class GossipAdmin(admin.ModelAdmin):
    model = Gossip
    list_display = ('title', 'date')


admin.site.register(Gossip, GossipAdmin)
