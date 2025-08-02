# 📜 Majo's Spell Enchantment

> An enchantment expansion for **Iron's Spells 'n Spellbooks**, adding new enchantments that interact with spells, mana, and casting mechanics.

***

## ✨ Overview

Majo's Spell Enchantment introduces a set of useful enchantments designed to enhance spellcasting gameplay, offering bonuses such as mana capacity, cooldown reduction, conditional spell power, and unique mobility boosts.

***

## 🔮 Enchantments List

| Enchantment (EN)      |Enchantment (CN) |Effect                                                                                    |Applicable Gear |
| --------------------- |---------------- |----------------------------------------------------------------------------------------- |--------------- |
| <strong>Vas Mana</strong> |魔力扩容             |Increases maximum mana.                                                                   |Helmet          |
| <strong>Cantus Celeris</strong> |迅捷咏唱             |Reduces spell cooldown.                                                                   |Staff, Weapon   |
| <strong>Messor Vitae</strong> |灵魂收割             |Restores mana on mob kill (scaled by target HP).                                          |Staff, Weapon   |
| <strong>Mortis Impetus</strong> |死亡驱动             |Reduces all spell cooldowns on mob kill.                                                  |Staff, Weapon   |
| <strong>Cor Inferni</strong> |冥火鼓动             |Increases non-Ice spell level when in Nether or burning.                                  |Spellbook       |
| <strong>Potentia Desperata</strong> |绝望之力             |Increases spell power at low HP (scales with enchantment level).                          |Chestplate      |
| <strong>Gratia Oceani</strong> |沧海之赐             |Reduces mana cost while in water or rain.                                                 |Leggings        |
| <strong>Cursus Phasium</strong> |相位猛冲             |Grants Speed buff after hurt mob 3 times with spells in quick succession.                 |Boots           |
| <strong>Aria Noctis</strong> |星夜咏叹             |Reduces cast time at night or when overhead is blocked. Incompatible with Hymnus Aurorae. |Spellbook       |
| <strong>Hymnus Aurorae</strong> |晨曦颂歌             |Reduces cast time during daytime with clear overhead. Incompatible with Aria Noctis.      |Spellbook       |
| <strong>Sacramentum Sanguinis</strong> |鲜血誓约             |Restores mana when taking damage.                                                         |Chestplate      |
| <span style="color: #e03e2d;"><strong>Zoophony (Curse)</strong></span> |<span style="color: #e03e2d;">礼堂之声</span> |Plays random animal sounds when casting spells.                                           |Helmet          |

***

## ⚙️ Compatibility

*   Uses event-based triggers for performance efficiency.
*   Configurable parameters for mana regen, thresholds, cooldown reduction, etc.

***

## 🛠 Configuration

Adjust enchantment effects in `MajoSpellEnchantmentConfig.toml`:

*   Mana restore factor & cap for **Messor Vitae**
*   HP thresholds & bonus per level for **Potentia Desperata**
*   Mana reduction factor for **Gratia Oceani**
*   Speed buff duration for **Cursus Phasium**
*   Cooldown reduction rate for **Mortis Impetus**

***

> _Master the arcane arts, push your magic to the limits — Majo’s enchantments will enhance your spellcasting experience._
