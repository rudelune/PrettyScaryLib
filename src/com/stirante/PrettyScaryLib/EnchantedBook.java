package com.stirante.PrettyScaryLib;

import net.minecraft.server.v1_4_6.NBTTagCompound;
import net.minecraft.server.v1_4_6.NBTTagList;

import org.bukkit.craftbukkit.v1_4_6.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * Class, that allows editing enchanted books.
 */
public class EnchantedBook {
	
	/**
	 * Checks if item is applicable.
	 * 
	 * @param item
	 *            the item to check
	 * @return true, if is applicable
	 */
	public static boolean isApplicable(ItemStack item) {
		switch (item.getTypeId()) {
			case 403:
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * Adds enchantment.
	 * 
	 * @param item
	 *            item
	 * @param ench
	 *            enchantment
	 * @param lvl
	 *            level
	 * @return item stack
	 */
	public static ItemStack addExplosion(ItemStack item, Enchantment ench,
			short lvl) {
		if (!isApplicable(item)) return null;
		net.minecraft.server.v1_4_6.ItemStack itemStack = CraftItemStack.asNMSCopy(item);
		if (itemStack == null) return null;
		NBTTagCompound tag = itemStack.tag;
		if (tag == null) {
			tag = new NBTTagCompound();
			tag.set("StoredEnchantments", new NBTTagList());
			itemStack.tag = tag;
		}
		
		NBTTagList list = tag.getList("StoredEnchantments");
		NBTTagCompound tag1 = new NBTTagCompound();
		tag1.setShort("id", (short) ench.getId());
		tag1.setShort("lvl", lvl);
		list.add(tag1);
		tag.set("StoredEnchantments", list);
		return CraftItemStack.asCraftMirror(itemStack);
	}
	
	/**
	 * Gets enchantments.
	 * 
	 * @param item
	 *            item
	 * @return enchantments
	 */
	public static StoredEnchantment[] getEnchantments(ItemStack item) {
		if (!isApplicable(item)) return null;
		net.minecraft.server.v1_4_6.ItemStack itemStack = CraftItemStack.asNMSCopy(item);
		if (itemStack == null) return null;
		NBTTagCompound tag = itemStack.tag;
		if (tag == null) {
			tag = new NBTTagCompound();
			tag.set("StoredEnchantments", new NBTTagList());
			itemStack.tag = tag;
		}
		NBTTagList list = tag.getList("StoredEnchantments");
		StoredEnchantment[] exps = new StoredEnchantment[list.size()];
		for (int i = 0; i < list.size(); i++)
			exps[i] = new StoredEnchantment((NBTTagCompound) list.get(i));
		return exps;
	}
	
	/**
	 * Adds enchantment.
	 * 
	 * @param item
	 *            item
	 * @param ench
	 *            enchantment
	 * @return item stack
	 */
	public static ItemStack addExplosion(ItemStack item, StoredEnchantment ench) {
		if (!isApplicable(item)) return null;
		net.minecraft.server.v1_4_6.ItemStack itemStack = CraftItemStack.asNMSCopy(item);
		if (itemStack == null) return null;
		NBTTagCompound tag = itemStack.tag;
		if (tag == null) {
			tag = new NBTTagCompound();
			tag.set("StoredEnchantments", new NBTTagList());
			itemStack.tag = tag;
		}
		
		NBTTagList list = tag.getList("StoredEnchantments");
		list.add(ench.getTag());
		tag.set("StoredEnchantments", list);
		return CraftItemStack.asBukkitCopy(itemStack);
	}
}
