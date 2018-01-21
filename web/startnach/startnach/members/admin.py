from django.contrib import admin
from startnach.members.models import Member

class MembersAdmin(admin.ModelAdmin):
    model = Member
    list_display = ('name', 'points')

admin.site.register(Member, MembersAdmin)
